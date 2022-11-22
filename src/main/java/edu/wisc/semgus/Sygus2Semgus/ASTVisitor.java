/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.Sygus2Semgus;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class ASTVisitor extends QSygusParserBaseVisitor<ProgramNode> {

    /* gTerm       :   SYMBOL
            |   literal
            |   '(' SYMBOL gTermStar ')'
            |   '(' 'Constant' sortExpr ')'
            |   '(' 'Vairiable' sortExpr ')'
            |   '(' 'InputVariable' sortExpr ')'
            |   '(' 'LocalVariable' sortExpr ')'
            |   letGTerm
            ;
    */
    @Override
    public GTermNode visitGTerm(QSygusParserParser.GTermContext ctx) {
        if(ctx.gTermStar() != null){
            List<GTermNode> children = new ArrayList<GTermNode>();
            QSygusParserParser.GTermStarContext gtermstar= ctx.gTermStar();
            while(gtermstar.gTermStar() !=null){
                children.add(0,visitGTerm(gtermstar.gTerm()));
                gtermstar = gtermstar.gTermStar();
            }
            return new GTermNode(ctx.SYMBOL().getText(),children);
        }
        return new GTermNode(ctx.children.get(0).getText(), null);
    }

    @Override
    public NTNode visitNtDef(QSygusParserParser.NtDefContext ctx) {
        String ntName = ctx.SYMBOL().getText();
        String sort = getSplitedText(ctx.sortExpr());
        List<RuleNode> rules = new ArrayList<RuleNode>();
        QSygusParserParser.GTermPlusContext gtermplus = ctx.gTermPlus();
        while(gtermplus.gTermPlus() != null){
            rules.add(0,visitGTermWeighted(gtermplus.gTermWeighted()));
            gtermplus = gtermplus.gTermPlus();
        }
        rules.add(0,visitGTermWeighted(gtermplus.gTermWeighted()));
        return new NTNode(ntName, sort, rules);
    }

    @Override
    public RuleNode visitGTermWeighted(QSygusParserParser.GTermWeightedContext ctx) {
        List<String> weight = new ArrayList<String>();
        QSygusParserParser.LiteralPlusContext literalPlus = ctx.literalPlus();
        if(ctx.literalPlus()!=null) {
            while (literalPlus.literalPlus() != null) {
                weight.add(0, literalPlus.literal().getText());
                literalPlus = literalPlus.literalPlus();
            }
            weight.add(0, literalPlus.literal().getText());
        }
        return new RuleNode(weight, visitGTerm(ctx.gTerm()));
    }

    @Override
    public GrammarNode visitSynthFunCmd(QSygusParserParser.SynthFunCmdContext ctx) {
        String funName = ctx.SYMBOL().getText();
        String argList = getSplitedText(ctx.argList());

        List<String> argList_list = new ArrayList<>();
        List<SortNode> argSort_list = new ArrayList<>();
        QSygusParserParser.SymbolSortPairStarContext sspsc = ctx.argList().symbolSortPairStar();
        while(sspsc.symbolSortPairStar() != null){
            argList_list.add(0, sspsc.symbolSortPair().SYMBOL().getText());
            argSort_list.add(0, visitSortExpr(sspsc.symbolSortPair().sortExpr()));
            sspsc = sspsc.symbolSortPairStar();
        }

        String sort = getSplitedText(ctx.sortExpr());

        List<NTNode> ntNodes = new ArrayList<NTNode>();
        QSygusParserParser.NtDefPlusContext ntdefPlus = ctx.ntDefPlus();
        while(ntdefPlus.ntDefPlus() != null){
            ntNodes.add(0, visitNtDef(ntdefPlus.ntDef()));
            ntdefPlus = ntdefPlus.ntDefPlus();
        }
        ntNodes.add(0, visitNtDef(ntdefPlus.ntDef()));
        GrammarNode result =  new GrammarNode(funName,argList,sort,ntNodes, argList_list, argSort_list);
        result.funcSort_node = visitSortExpr(ctx.sortExpr());
        return result;
    }

    @Override
    public SortNode visitSortExpr(QSygusParserParser.SortExprContext ctx){
        String type = ctx.getText();
        if(ctx.INTCONST() != null) {
            try{
                return new SortNode(SortNode.BV, Integer.parseInt(ctx.INTCONST().getText()));
            }catch(NumberFormatException e){
                System.out.println("fail to parse sort: " + type);
                return null;
            }
        }
        switch(type){
            case "Bool":
                return new SortNode(SortNode.BOOL);
            case "Int":
                return new SortNode(SortNode.INT);
            case "Real":
                return new SortNode(SortNode.REAL);
            default:
                System.out.println("fail to parse sort: " + type);
                return null;
        }
    }

    @Override
    public TermNode visitTerm(QSygusParserParser.TermContext ctx) {
        String symbol;
        List<TermNode> children = new ArrayList<TermNode>();
        if(ctx.termStar() != null){
            symbol = ctx.SYMBOL().getText();
            QSygusParserParser.TermStarContext termStart = ctx.termStar();
            while(termStart.termStar() != null){
                children.add(0,visitTerm(termStart.term()));
                termStart = termStart.termStar();
            }
        }else{
            symbol = ctx.children.get(0).getText();
        }
        return new TermNode(symbol, children);
    }
    @Override
    public TermNode visitFunDefCmd(QSygusParserParser.FunDefCmdContext ctx){
        return visitTerm(ctx.term());
    }


    public String getSplitedText(ParseTree ctx){
        String result = "";
        if(ctx.getChildCount() != 0) {
            for (int i = 0; i < ctx.getChildCount(); i++) {
                result = result + " " + getSplitedText(ctx.getChild(i));
            }
        }else{
            return ctx.getText();
        }
        return result;
    }
}
