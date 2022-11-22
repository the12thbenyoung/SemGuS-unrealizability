/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.Sygus2Semgus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class GrammarNode extends ProgramNode {
    public List<NTNode> getNtNodes() {
        return ntNodes;
    }

    List<NTNode> ntNodes;
    public String funName;
    public String argList_string;
    public List<String> argList_list;
    public List<SortNode> argSort_list;
    public String funcSort_string;
    public SortNode funcSort_node;

    Map<String, Integer> idDic;
    Integer maxId;

    // Parse SyGuS file path and return resulting GrammarNode
    public static GrammarNode parseToGrammarNode(String grammarPath) throws FileNotFoundException {
        String grammarString = new Scanner(new File(grammarPath)).useDelimiter("\\Z").next();
        ANTLRInputStream inputStream = new ANTLRInputStream(grammarString);
        QSygusParserLexer lexer = new QSygusParserLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QSygusParserParser parser = new QSygusParserParser(tokens);
        ParseTree parseTree = parser.synthFunCmd();
        return (GrammarNode)new ASTVisitor().visit(parseTree);
    }

    public GrammarNode(String funName, String argList, String sort, List<NTNode> ntNodes){
        this.funName = funName;
        this.argList_string = argList;
        this.funcSort_string = sort;
        this.ntNodes = ntNodes;
        this.argList_list = new ArrayList<>();
        this.argSort_list = new ArrayList<>();
    }
    public GrammarNode(String funName, String argList, String sort, List<NTNode> ntNodes, List<String> argList_list, List<SortNode> argSort_list){
        this(funName,argList,sort,ntNodes);
        this.argList_list = argList_list;
        this.argSort_list = argSort_list;
    }

    @Override
    public  String toString(){
        String result = "( synth-fun " + funName + ' ' +argList_string + ' ' + funcSort_string + " (\n";
        for(NTNode node: ntNodes){
            result = result  + node.toString() + "\n";
        }
        return result + "))";
    }
}