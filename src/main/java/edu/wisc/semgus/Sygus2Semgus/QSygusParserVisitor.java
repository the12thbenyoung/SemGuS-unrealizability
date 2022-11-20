// Generated from QSygusParser.g4 by ANTLR 4.5.3
package edu.wisc.semgus.Sygus2Semgus;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QSygusParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QSygusParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(QSygusParserParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(QSygusParserParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#setLogicCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetLogicCmd(QSygusParserParser.SetLogicCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#weightPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeightPlus(QSygusParserParser.WeightPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#weight}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeight(QSygusParserParser.WeightContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#setWeightCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetWeightCmd(QSygusParserParser.SetWeightCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#cmdPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPlus(QSygusParserParser.CmdPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(QSygusParserParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#weightOptimizationCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeightOptimizationCmd(QSygusParserParser.WeightOptimizationCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#weightPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeightPair(QSygusParserParser.WeightPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#weightConstraintCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeightConstraintCmd(QSygusParserParser.WeightConstraintCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#varDeclCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclCmd(QSygusParserParser.VarDeclCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#sortDefCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortDefCmd(QSygusParserParser.SortDefCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#sortExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortExpr(QSygusParserParser.SortExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#boolConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolConst(QSygusParserParser.BoolConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#enumConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConst(QSygusParserParser.EnumConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#ecList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEcList(QSygusParserParser.EcListContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#symbolPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolPlus(QSygusParserParser.SymbolPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#setOptsCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetOptsCmd(QSygusParserParser.SetOptsCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#optList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptList(QSygusParserParser.OptListContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#symbolPairPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolPairPlus(QSygusParserParser.SymbolPairPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#symbolPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolPair(QSygusParserParser.SymbolPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#funDefCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDefCmd(QSygusParserParser.FunDefCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#funDeclCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDeclCmd(QSygusParserParser.FunDeclCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#sortStar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortStar(QSygusParserParser.SortStarContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(QSygusParserParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#symbolSortPairStar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolSortPairStar(QSygusParserParser.SymbolSortPairStarContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#symbolSortPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolSortPair(QSygusParserParser.SymbolSortPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(QSygusParserParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#letTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetTerm(QSygusParserParser.LetTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#letBindingTermPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetBindingTermPlus(QSygusParserParser.LetBindingTermPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#letBindingTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetBindingTerm(QSygusParserParser.LetBindingTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#termStar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermStar(QSygusParserParser.TermStarContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(QSygusParserParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#ntDefPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNtDefPlus(QSygusParserParser.NtDefPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#ntDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNtDef(QSygusParserParser.NtDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#gTermPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGTermPlus(QSygusParserParser.GTermPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#checkSynthCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckSynthCmd(QSygusParserParser.CheckSynthCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#constraintCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraintCmd(QSygusParserParser.ConstraintCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#synthFunCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynthFunCmd(QSygusParserParser.SynthFunCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#gTermWeighted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGTermWeighted(QSygusParserParser.GTermWeightedContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#literalPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralPlus(QSygusParserParser.LiteralPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#gTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGTerm(QSygusParserParser.GTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#letGTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetGTerm(QSygusParserParser.LetGTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#letBindingGTermPlus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetBindingGTermPlus(QSygusParserParser.LetBindingGTermPlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#letBindingGTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetBindingGTerm(QSygusParserParser.LetBindingGTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link QSygusParserParser#gTermStar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGTermStar(QSygusParserParser.GTermStarContext ctx);
}