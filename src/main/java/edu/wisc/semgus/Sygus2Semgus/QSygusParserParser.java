// Generated from QSygusParser.g4 by ANTLR 4.5.3
package edu.wisc.semgus.Sygus2Semgus;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QSygusParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, WS=33, INTCONST=34, BVCONST=35, REALCONST=36, SYMBOL=37, QUOTEDLIT=38;
	public static final int
		RULE_start = 0, RULE_prog = 1, RULE_setLogicCmd = 2, RULE_weightPlus = 3, 
		RULE_weight = 4, RULE_setWeightCmd = 5, RULE_cmdPlus = 6, RULE_cmd = 7, 
		RULE_weightOptimizationCmd = 8, RULE_weightPair = 9, RULE_weightConstraintCmd = 10, 
		RULE_varDeclCmd = 11, RULE_sortDefCmd = 12, RULE_sortExpr = 13, RULE_boolConst = 14, 
		RULE_enumConst = 15, RULE_ecList = 16, RULE_symbolPlus = 17, RULE_setOptsCmd = 18, 
		RULE_optList = 19, RULE_symbolPairPlus = 20, RULE_symbolPair = 21, RULE_funDefCmd = 22, 
		RULE_funDeclCmd = 23, RULE_sortStar = 24, RULE_argList = 25, RULE_symbolSortPairStar = 26, 
		RULE_symbolSortPair = 27, RULE_term = 28, RULE_letTerm = 29, RULE_letBindingTermPlus = 30, 
		RULE_letBindingTerm = 31, RULE_termStar = 32, RULE_literal = 33, RULE_ntDefPlus = 34, 
		RULE_ntDef = 35, RULE_gTermPlus = 36, RULE_checkSynthCmd = 37, RULE_constraintCmd = 38, 
		RULE_synthFunCmd = 39, RULE_gTermWeighted = 40, RULE_literalPlus = 41, 
		RULE_gTerm = 42, RULE_letGTerm = 43, RULE_letBindingGTermPlus = 44, RULE_letBindingGTerm = 45, 
		RULE_gTermStar = 46;
	public static final String[] ruleNames = {
		"start", "prog", "setLogicCmd", "weightPlus", "weight", "setWeightCmd", 
		"cmdPlus", "cmd", "weightOptimizationCmd", "weightPair", "weightConstraintCmd", 
		"varDeclCmd", "sortDefCmd", "sortExpr", "boolConst", "enumConst", "ecList", 
		"symbolPlus", "setOptsCmd", "optList", "symbolPairPlus", "symbolPair", 
		"funDefCmd", "funDeclCmd", "sortStar", "argList", "symbolSortPairStar", 
		"symbolSortPair", "term", "letTerm", "letBindingTermPlus", "letBindingTerm", 
		"termStar", "literal", "ntDefPlus", "ntDef", "gTermPlus", "checkSynthCmd", 
		"constraintCmd", "synthFunCmd", "gTermWeighted", "literalPlus", "gTerm", 
		"letGTerm", "letBindingGTermPlus", "letBindingGTerm", "gTermStar"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "'set-logic'", "')'", "'TROP'", "'PROB'", "'BOOL'", "'set-weight'", 
		"'optimize'", "'weight-constraint'", "'declare-var'", "'define-sort'", 
		"'BitVec'", "'Bool'", "'Int'", "'Real'", "'Enum'", "'Array'", "'true'", 
		"'false'", "'::'", "'set-options'", "'define-fun'", "'declare-fun'", "'let'", 
		"'check-synth'", "'constraint'", "'synth-fun'", "':'", "'Constant'", "'Vairiable'", 
		"'InputVariable'", "'LocalVariable'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "WS", "INTCONST", 
		"BVCONST", "REALCONST", "SYMBOL", "QUOTEDLIT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "QSygusParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QSygusParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			prog();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgContext extends ParserRuleContext {
		public SetWeightCmdContext setWeightCmd() {
			return getRuleContext(SetWeightCmdContext.class,0);
		}
		public CmdPlusContext cmdPlus() {
			return getRuleContext(CmdPlusContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			setWeightCmd();
			setState(97);
			cmdPlus(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetLogicCmdContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SetLogicCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setLogicCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSetLogicCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetLogicCmdContext setLogicCmd() throws RecognitionException {
		SetLogicCmdContext _localctx = new SetLogicCmdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_setLogicCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__0);
			setState(100);
			match(T__1);
			setState(101);
			match(SYMBOL);
			setState(102);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WeightPlusContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public WeightContext weight() {
			return getRuleContext(WeightContext.class,0);
		}
		public WeightPlusContext weightPlus() {
			return getRuleContext(WeightPlusContext.class,0);
		}
		public WeightPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weightPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitWeightPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WeightPlusContext weightPlus() throws RecognitionException {
		return weightPlus(0);
	}

	private WeightPlusContext weightPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		WeightPlusContext _localctx = new WeightPlusContext(_ctx, _parentState);
		WeightPlusContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_weightPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(105);
			match(T__0);
			setState(106);
			match(SYMBOL);
			setState(107);
			weight();
			setState(108);
			match(T__2);
			}
			_ctx.stop = _input.LT(-1);
			setState(118);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new WeightPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_weightPlus);
					setState(110);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(111);
					match(T__0);
					setState(112);
					match(SYMBOL);
					setState(113);
					weight();
					setState(114);
					match(T__2);
					}
					} 
				}
				setState(120);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class WeightContext extends ParserRuleContext {
		public WeightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weight; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitWeight(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WeightContext weight() throws RecognitionException {
		WeightContext _localctx = new WeightContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_weight);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetWeightCmdContext extends ParserRuleContext {
		public WeightPlusContext weightPlus() {
			return getRuleContext(WeightPlusContext.class,0);
		}
		public SetWeightCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setWeightCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSetWeightCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetWeightCmdContext setWeightCmd() throws RecognitionException {
		SetWeightCmdContext _localctx = new SetWeightCmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_setWeightCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(T__0);
			setState(124);
			match(T__6);
			setState(125);
			weightPlus(0);
			setState(126);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdPlusContext extends ParserRuleContext {
		public CmdContext cmd() {
			return getRuleContext(CmdContext.class,0);
		}
		public CmdPlusContext cmdPlus() {
			return getRuleContext(CmdPlusContext.class,0);
		}
		public CmdPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitCmdPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdPlusContext cmdPlus() throws RecognitionException {
		return cmdPlus(0);
	}

	private CmdPlusContext cmdPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CmdPlusContext _localctx = new CmdPlusContext(_ctx, _parentState);
		CmdPlusContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_cmdPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(129);
			cmd();
			}
			_ctx.stop = _input.LT(-1);
			setState(135);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CmdPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_cmdPlus);
					setState(131);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(132);
					cmd();
					}
					} 
				}
				setState(137);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public SetLogicCmdContext setLogicCmd() {
			return getRuleContext(SetLogicCmdContext.class,0);
		}
		public FunDefCmdContext funDefCmd() {
			return getRuleContext(FunDefCmdContext.class,0);
		}
		public FunDeclCmdContext funDeclCmd() {
			return getRuleContext(FunDeclCmdContext.class,0);
		}
		public SynthFunCmdContext synthFunCmd() {
			return getRuleContext(SynthFunCmdContext.class,0);
		}
		public CheckSynthCmdContext checkSynthCmd() {
			return getRuleContext(CheckSynthCmdContext.class,0);
		}
		public ConstraintCmdContext constraintCmd() {
			return getRuleContext(ConstraintCmdContext.class,0);
		}
		public SortDefCmdContext sortDefCmd() {
			return getRuleContext(SortDefCmdContext.class,0);
		}
		public SetOptsCmdContext setOptsCmd() {
			return getRuleContext(SetOptsCmdContext.class,0);
		}
		public WeightConstraintCmdContext weightConstraintCmd() {
			return getRuleContext(WeightConstraintCmdContext.class,0);
		}
		public WeightOptimizationCmdContext weightOptimizationCmd() {
			return getRuleContext(WeightOptimizationCmdContext.class,0);
		}
		public VarDeclCmdContext varDeclCmd() {
			return getRuleContext(VarDeclCmdContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmd);
		try {
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				setLogicCmd();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				funDefCmd();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				funDeclCmd();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(141);
				synthFunCmd();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(142);
				checkSynthCmd();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(143);
				constraintCmd();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(144);
				sortDefCmd();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(145);
				setOptsCmd();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(146);
				weightConstraintCmd();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(147);
				weightOptimizationCmd();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(148);
				varDeclCmd();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WeightOptimizationCmdContext extends ParserRuleContext {
		public WeightPairContext weightPair() {
			return getRuleContext(WeightPairContext.class,0);
		}
		public WeightOptimizationCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weightOptimizationCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitWeightOptimizationCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WeightOptimizationCmdContext weightOptimizationCmd() throws RecognitionException {
		WeightOptimizationCmdContext _localctx = new WeightOptimizationCmdContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_weightOptimizationCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__0);
			setState(152);
			match(T__7);
			setState(153);
			weightPair();
			setState(154);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WeightPairContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SymbolPlusContext symbolPlus() {
			return getRuleContext(SymbolPlusContext.class,0);
		}
		public WeightPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weightPair; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitWeightPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WeightPairContext weightPair() throws RecognitionException {
		WeightPairContext _localctx = new WeightPairContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_weightPair);
		try {
			setState(162);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				match(T__0);
				setState(157);
				match(SYMBOL);
				setState(158);
				symbolPlus(0);
				setState(159);
				match(T__2);
				}
				break;
			case SYMBOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(SYMBOL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WeightConstraintCmdContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public WeightConstraintCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weightConstraintCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitWeightConstraintCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WeightConstraintCmdContext weightConstraintCmd() throws RecognitionException {
		WeightConstraintCmdContext _localctx = new WeightConstraintCmdContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_weightConstraintCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(T__0);
			setState(165);
			match(T__8);
			setState(166);
			term();
			setState(167);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclCmdContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public VarDeclCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitVarDeclCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclCmdContext varDeclCmd() throws RecognitionException {
		VarDeclCmdContext _localctx = new VarDeclCmdContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_varDeclCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__0);
			setState(170);
			match(T__9);
			setState(171);
			match(SYMBOL);
			setState(172);
			sortExpr();
			setState(173);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortDefCmdContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public SortDefCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sortDefCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSortDefCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SortDefCmdContext sortDefCmd() throws RecognitionException {
		SortDefCmdContext _localctx = new SortDefCmdContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sortDefCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(T__0);
			setState(176);
			match(T__10);
			setState(177);
			match(SYMBOL);
			setState(178);
			sortExpr();
			setState(179);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortExprContext extends ParserRuleContext {
		public TerminalNode INTCONST() { return getToken(QSygusParserParser.INTCONST, 0); }
		public EcListContext ecList() {
			return getRuleContext(EcListContext.class,0);
		}
		public List<SortExprContext> sortExpr() {
			return getRuleContexts(SortExprContext.class);
		}
		public SortExprContext sortExpr(int i) {
			return getRuleContext(SortExprContext.class,i);
		}
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SortExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sortExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSortExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SortExprContext sortExpr() throws RecognitionException {
		SortExprContext _localctx = new SortExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_sortExpr);
		try {
			setState(200);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				match(T__0);
				setState(182);
				match(T__11);
				setState(183);
				match(INTCONST);
				setState(184);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				match(T__12);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(186);
				match(T__13);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(187);
				match(T__14);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(188);
				match(T__0);
				setState(189);
				match(T__15);
				setState(190);
				ecList();
				setState(191);
				match(T__2);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(193);
				match(T__0);
				setState(194);
				match(T__16);
				setState(195);
				sortExpr();
				setState(196);
				sortExpr();
				setState(197);
				match(T__2);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(199);
				match(SYMBOL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolConstContext extends ParserRuleContext {
		public BoolConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolConst; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitBoolConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolConstContext boolConst() throws RecognitionException {
		BoolConstContext _localctx = new BoolConstContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_boolConst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if ( !(_la==T__17 || _la==T__18) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstContext extends ParserRuleContext {
		public List<TerminalNode> SYMBOL() { return getTokens(QSygusParserParser.SYMBOL); }
		public TerminalNode SYMBOL(int i) {
			return getToken(QSygusParserParser.SYMBOL, i);
		}
		public EnumConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConst; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitEnumConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumConstContext enumConst() throws RecognitionException {
		EnumConstContext _localctx = new EnumConstContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_enumConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(SYMBOL);
			setState(205);
			match(T__19);
			setState(206);
			match(SYMBOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EcListContext extends ParserRuleContext {
		public SymbolPlusContext symbolPlus() {
			return getRuleContext(SymbolPlusContext.class,0);
		}
		public EcListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ecList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitEcList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EcListContext ecList() throws RecognitionException {
		EcListContext _localctx = new EcListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ecList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(T__0);
			setState(209);
			symbolPlus(0);
			setState(210);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolPlusContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SymbolPlusContext symbolPlus() {
			return getRuleContext(SymbolPlusContext.class,0);
		}
		public SymbolPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSymbolPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolPlusContext symbolPlus() throws RecognitionException {
		return symbolPlus(0);
	}

	private SymbolPlusContext symbolPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SymbolPlusContext _localctx = new SymbolPlusContext(_ctx, _parentState);
		SymbolPlusContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_symbolPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(213);
			match(SYMBOL);
			}
			_ctx.stop = _input.LT(-1);
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SymbolPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_symbolPlus);
					setState(215);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(216);
					match(SYMBOL);
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SetOptsCmdContext extends ParserRuleContext {
		public OptListContext optList() {
			return getRuleContext(OptListContext.class,0);
		}
		public SetOptsCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setOptsCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSetOptsCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetOptsCmdContext setOptsCmd() throws RecognitionException {
		SetOptsCmdContext _localctx = new SetOptsCmdContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_setOptsCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(T__0);
			setState(223);
			match(T__20);
			setState(224);
			optList();
			setState(225);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptListContext extends ParserRuleContext {
		public SymbolPairPlusContext symbolPairPlus() {
			return getRuleContext(SymbolPairPlusContext.class,0);
		}
		public OptListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitOptList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptListContext optList() throws RecognitionException {
		OptListContext _localctx = new OptListContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_optList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(T__0);
			setState(228);
			symbolPairPlus(0);
			setState(229);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolPairPlusContext extends ParserRuleContext {
		public SymbolPairContext symbolPair() {
			return getRuleContext(SymbolPairContext.class,0);
		}
		public SymbolPairPlusContext symbolPairPlus() {
			return getRuleContext(SymbolPairPlusContext.class,0);
		}
		public SymbolPairPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolPairPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSymbolPairPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolPairPlusContext symbolPairPlus() throws RecognitionException {
		return symbolPairPlus(0);
	}

	private SymbolPairPlusContext symbolPairPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SymbolPairPlusContext _localctx = new SymbolPairPlusContext(_ctx, _parentState);
		SymbolPairPlusContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_symbolPairPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(232);
			symbolPair();
			}
			_ctx.stop = _input.LT(-1);
			setState(238);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SymbolPairPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_symbolPairPlus);
					setState(234);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(235);
					symbolPair();
					}
					} 
				}
				setState(240);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SymbolPairContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public TerminalNode QUOTEDLIT() { return getToken(QSygusParserParser.QUOTEDLIT, 0); }
		public SymbolPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolPair; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSymbolPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolPairContext symbolPair() throws RecognitionException {
		SymbolPairContext _localctx = new SymbolPairContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_symbolPair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(T__0);
			setState(242);
			match(SYMBOL);
			setState(243);
			match(QUOTEDLIT);
			setState(244);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunDefCmdContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public FunDefCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDefCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitFunDefCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDefCmdContext funDefCmd() throws RecognitionException {
		FunDefCmdContext _localctx = new FunDefCmdContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_funDefCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(T__0);
			setState(247);
			match(T__21);
			setState(248);
			match(SYMBOL);
			setState(249);
			argList();
			setState(250);
			sortExpr();
			setState(251);
			term();
			setState(252);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunDeclCmdContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SortStarContext sortStar() {
			return getRuleContext(SortStarContext.class,0);
		}
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public FunDeclCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDeclCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitFunDeclCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDeclCmdContext funDeclCmd() throws RecognitionException {
		FunDeclCmdContext _localctx = new FunDeclCmdContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_funDeclCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(T__0);
			setState(255);
			match(T__22);
			setState(256);
			match(SYMBOL);
			setState(257);
			match(T__0);
			setState(258);
			sortStar(0);
			setState(259);
			match(T__2);
			setState(260);
			sortExpr();
			setState(261);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortStarContext extends ParserRuleContext {
		public SortStarContext sortStar() {
			return getRuleContext(SortStarContext.class,0);
		}
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public SortStarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sortStar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSortStar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SortStarContext sortStar() throws RecognitionException {
		return sortStar(0);
	}

	private SortStarContext sortStar(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SortStarContext _localctx = new SortStarContext(_ctx, _parentState);
		SortStarContext _prevctx = _localctx;
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_sortStar, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(268);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SortStarContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_sortStar);
					setState(264);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(265);
					sortExpr();
					}
					} 
				}
				setState(270);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public SymbolSortPairStarContext symbolSortPairStar() {
			return getRuleContext(SymbolSortPairStarContext.class,0);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_argList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(T__0);
			setState(272);
			symbolSortPairStar(0);
			setState(273);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolSortPairStarContext extends ParserRuleContext {
		public SymbolSortPairStarContext symbolSortPairStar() {
			return getRuleContext(SymbolSortPairStarContext.class,0);
		}
		public SymbolSortPairContext symbolSortPair() {
			return getRuleContext(SymbolSortPairContext.class,0);
		}
		public SymbolSortPairStarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolSortPairStar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSymbolSortPairStar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolSortPairStarContext symbolSortPairStar() throws RecognitionException {
		return symbolSortPairStar(0);
	}

	private SymbolSortPairStarContext symbolSortPairStar(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SymbolSortPairStarContext _localctx = new SymbolSortPairStarContext(_ctx, _parentState);
		SymbolSortPairStarContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_symbolSortPairStar, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(280);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SymbolSortPairStarContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_symbolSortPairStar);
					setState(276);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(277);
					symbolSortPair();
					}
					} 
				}
				setState(282);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SymbolSortPairContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public SymbolSortPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolSortPair; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSymbolSortPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolSortPairContext symbolSortPair() throws RecognitionException {
		SymbolSortPairContext _localctx = new SymbolSortPairContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_symbolSortPair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(T__0);
			setState(284);
			match(SYMBOL);
			setState(285);
			sortExpr();
			setState(286);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public TermStarContext termStar() {
			return getRuleContext(TermStarContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LetTermContext letTerm() {
			return getRuleContext(LetTermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_term);
		try {
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(T__0);
				setState(289);
				match(SYMBOL);
				setState(290);
				termStar(0);
				setState(291);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				literal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(294);
				match(SYMBOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(295);
				letTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetTermContext extends ParserRuleContext {
		public LetBindingTermPlusContext letBindingTermPlus() {
			return getRuleContext(LetBindingTermPlusContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public LetTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letTerm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitLetTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetTermContext letTerm() throws RecognitionException {
		LetTermContext _localctx = new LetTermContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_letTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(T__0);
			setState(299);
			match(T__23);
			setState(300);
			match(T__0);
			setState(301);
			letBindingTermPlus(0);
			setState(302);
			match(T__2);
			setState(303);
			term();
			setState(304);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetBindingTermPlusContext extends ParserRuleContext {
		public LetBindingTermContext letBindingTerm() {
			return getRuleContext(LetBindingTermContext.class,0);
		}
		public LetBindingTermPlusContext letBindingTermPlus() {
			return getRuleContext(LetBindingTermPlusContext.class,0);
		}
		public LetBindingTermPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letBindingTermPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitLetBindingTermPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetBindingTermPlusContext letBindingTermPlus() throws RecognitionException {
		return letBindingTermPlus(0);
	}

	private LetBindingTermPlusContext letBindingTermPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LetBindingTermPlusContext _localctx = new LetBindingTermPlusContext(_ctx, _parentState);
		LetBindingTermPlusContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_letBindingTermPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(307);
			letBindingTerm();
			}
			_ctx.stop = _input.LT(-1);
			setState(313);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LetBindingTermPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_letBindingTermPlus);
					setState(309);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(310);
					letBindingTerm();
					}
					} 
				}
				setState(315);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LetBindingTermContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public LetBindingTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letBindingTerm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitLetBindingTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetBindingTermContext letBindingTerm() throws RecognitionException {
		LetBindingTermContext _localctx = new LetBindingTermContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_letBindingTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(T__0);
			setState(317);
			match(SYMBOL);
			setState(318);
			sortExpr();
			setState(319);
			term();
			setState(320);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermStarContext extends ParserRuleContext {
		public TermStarContext termStar() {
			return getRuleContext(TermStarContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermStarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termStar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitTermStar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermStarContext termStar() throws RecognitionException {
		return termStar(0);
	}

	private TermStarContext termStar(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermStarContext _localctx = new TermStarContext(_ctx, _parentState);
		TermStarContext _prevctx = _localctx;
		int _startState = 64;
		enterRecursionRule(_localctx, 64, RULE_termStar, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(327);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermStarContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_termStar);
					setState(323);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(324);
					term();
					}
					} 
				}
				setState(329);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INTCONST() { return getToken(QSygusParserParser.INTCONST, 0); }
		public BoolConstContext boolConst() {
			return getRuleContext(BoolConstContext.class,0);
		}
		public TerminalNode BVCONST() { return getToken(QSygusParserParser.BVCONST, 0); }
		public EnumConstContext enumConst() {
			return getRuleContext(EnumConstContext.class,0);
		}
		public TerminalNode REALCONST() { return getToken(QSygusParserParser.REALCONST, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_literal);
		try {
			setState(335);
			switch (_input.LA(1)) {
			case INTCONST:
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				match(INTCONST);
				}
				break;
			case T__17:
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				boolConst();
				}
				break;
			case BVCONST:
				enterOuterAlt(_localctx, 3);
				{
				setState(332);
				match(BVCONST);
				}
				break;
			case SYMBOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(333);
				enumConst();
				}
				break;
			case REALCONST:
				enterOuterAlt(_localctx, 5);
				{
				setState(334);
				match(REALCONST);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NtDefPlusContext extends ParserRuleContext {
		public NtDefContext ntDef() {
			return getRuleContext(NtDefContext.class,0);
		}
		public NtDefPlusContext ntDefPlus() {
			return getRuleContext(NtDefPlusContext.class,0);
		}
		public NtDefPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ntDefPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitNtDefPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NtDefPlusContext ntDefPlus() throws RecognitionException {
		return ntDefPlus(0);
	}

	private NtDefPlusContext ntDefPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NtDefPlusContext _localctx = new NtDefPlusContext(_ctx, _parentState);
		NtDefPlusContext _prevctx = _localctx;
		int _startState = 68;
		enterRecursionRule(_localctx, 68, RULE_ntDefPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(338);
			ntDef();
			}
			_ctx.stop = _input.LT(-1);
			setState(344);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NtDefPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_ntDefPlus);
					setState(340);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(341);
					ntDef();
					}
					} 
				}
				setState(346);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NtDefContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public GTermPlusContext gTermPlus() {
			return getRuleContext(GTermPlusContext.class,0);
		}
		public NtDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ntDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitNtDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NtDefContext ntDef() throws RecognitionException {
		NtDefContext _localctx = new NtDefContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_ntDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			match(T__0);
			setState(348);
			match(SYMBOL);
			setState(349);
			sortExpr();
			setState(350);
			match(T__0);
			setState(351);
			gTermPlus(0);
			setState(352);
			match(T__2);
			setState(353);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GTermPlusContext extends ParserRuleContext {
		public GTermWeightedContext gTermWeighted() {
			return getRuleContext(GTermWeightedContext.class,0);
		}
		public GTermPlusContext gTermPlus() {
			return getRuleContext(GTermPlusContext.class,0);
		}
		public GTermPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gTermPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitGTermPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GTermPlusContext gTermPlus() throws RecognitionException {
		return gTermPlus(0);
	}

	private GTermPlusContext gTermPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		GTermPlusContext _localctx = new GTermPlusContext(_ctx, _parentState);
		GTermPlusContext _prevctx = _localctx;
		int _startState = 72;
		enterRecursionRule(_localctx, 72, RULE_gTermPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(356);
			gTermWeighted();
			}
			_ctx.stop = _input.LT(-1);
			setState(362);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new GTermPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_gTermPlus);
					setState(358);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(359);
					gTermWeighted();
					}
					} 
				}
				setState(364);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CheckSynthCmdContext extends ParserRuleContext {
		public CheckSynthCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkSynthCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitCheckSynthCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckSynthCmdContext checkSynthCmd() throws RecognitionException {
		CheckSynthCmdContext _localctx = new CheckSynthCmdContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_checkSynthCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			match(T__0);
			setState(366);
			match(T__24);
			setState(367);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintCmdContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ConstraintCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraintCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitConstraintCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintCmdContext constraintCmd() throws RecognitionException {
		ConstraintCmdContext _localctx = new ConstraintCmdContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_constraintCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(T__0);
			setState(370);
			match(T__25);
			setState(371);
			term();
			setState(372);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SynthFunCmdContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public NtDefPlusContext ntDefPlus() {
			return getRuleContext(NtDefPlusContext.class,0);
		}
		public SynthFunCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synthFunCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitSynthFunCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SynthFunCmdContext synthFunCmd() throws RecognitionException {
		SynthFunCmdContext _localctx = new SynthFunCmdContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_synthFunCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(T__0);
			setState(375);
			match(T__26);
			setState(376);
			match(SYMBOL);
			setState(377);
			argList();
			setState(378);
			sortExpr();
			setState(379);
			match(T__0);
			setState(380);
			ntDefPlus(0);
			setState(381);
			match(T__2);
			setState(382);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GTermWeightedContext extends ParserRuleContext {
		public GTermContext gTerm() {
			return getRuleContext(GTermContext.class,0);
		}
		public LiteralPlusContext literalPlus() {
			return getRuleContext(LiteralPlusContext.class,0);
		}
		public GTermWeightedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gTermWeighted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitGTermWeighted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GTermWeightedContext gTermWeighted() throws RecognitionException {
		GTermWeightedContext _localctx = new GTermWeightedContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_gTermWeighted);
		try {
			setState(391);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(384);
				match(T__0);
				setState(385);
				gTerm();
				setState(386);
				match(T__27);
				setState(387);
				literalPlus(0);
				setState(388);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(390);
				gTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralPlusContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralPlusContext literalPlus() {
			return getRuleContext(LiteralPlusContext.class,0);
		}
		public LiteralPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitLiteralPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralPlusContext literalPlus() throws RecognitionException {
		return literalPlus(0);
	}

	private LiteralPlusContext literalPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LiteralPlusContext _localctx = new LiteralPlusContext(_ctx, _parentState);
		LiteralPlusContext _prevctx = _localctx;
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_literalPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(394);
			literal();
			}
			_ctx.stop = _input.LT(-1);
			setState(400);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LiteralPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_literalPlus);
					setState(396);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(397);
					literal();
					}
					} 
				}
				setState(402);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class GTermContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public GTermStarContext gTermStar() {
			return getRuleContext(GTermStarContext.class,0);
		}
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public LetGTermContext letGTerm() {
			return getRuleContext(LetGTermContext.class,0);
		}
		public GTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gTerm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitGTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GTermContext gTerm() throws RecognitionException {
		GTermContext _localctx = new GTermContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_gTerm);
		try {
			setState(431);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(403);
				match(SYMBOL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
				literal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(405);
				match(T__0);
				setState(406);
				match(SYMBOL);
				setState(407);
				gTermStar(0);
				setState(408);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(410);
				match(T__0);
				setState(411);
				match(T__28);
				setState(412);
				sortExpr();
				setState(413);
				match(T__2);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(415);
				match(T__0);
				setState(416);
				match(T__29);
				setState(417);
				sortExpr();
				setState(418);
				match(T__2);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(420);
				match(T__0);
				setState(421);
				match(T__30);
				setState(422);
				sortExpr();
				setState(423);
				match(T__2);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(425);
				match(T__0);
				setState(426);
				match(T__31);
				setState(427);
				sortExpr();
				setState(428);
				match(T__2);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(430);
				letGTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetGTermContext extends ParserRuleContext {
		public LetBindingGTermPlusContext letBindingGTermPlus() {
			return getRuleContext(LetBindingGTermPlusContext.class,0);
		}
		public GTermContext gTerm() {
			return getRuleContext(GTermContext.class,0);
		}
		public LetGTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letGTerm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitLetGTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetGTermContext letGTerm() throws RecognitionException {
		LetGTermContext _localctx = new LetGTermContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_letGTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			match(T__0);
			setState(434);
			match(T__23);
			setState(435);
			match(T__0);
			setState(436);
			letBindingGTermPlus(0);
			setState(437);
			match(T__2);
			setState(438);
			gTerm();
			setState(439);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetBindingGTermPlusContext extends ParserRuleContext {
		public LetBindingGTermContext letBindingGTerm() {
			return getRuleContext(LetBindingGTermContext.class,0);
		}
		public LetBindingGTermPlusContext letBindingGTermPlus() {
			return getRuleContext(LetBindingGTermPlusContext.class,0);
		}
		public LetBindingGTermPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letBindingGTermPlus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitLetBindingGTermPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetBindingGTermPlusContext letBindingGTermPlus() throws RecognitionException {
		return letBindingGTermPlus(0);
	}

	private LetBindingGTermPlusContext letBindingGTermPlus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LetBindingGTermPlusContext _localctx = new LetBindingGTermPlusContext(_ctx, _parentState);
		LetBindingGTermPlusContext _prevctx = _localctx;
		int _startState = 88;
		enterRecursionRule(_localctx, 88, RULE_letBindingGTermPlus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(442);
			letBindingGTerm();
			}
			_ctx.stop = _input.LT(-1);
			setState(448);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LetBindingGTermPlusContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_letBindingGTermPlus);
					setState(444);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(445);
					letBindingGTerm();
					}
					} 
				}
				setState(450);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LetBindingGTermContext extends ParserRuleContext {
		public TerminalNode SYMBOL() { return getToken(QSygusParserParser.SYMBOL, 0); }
		public SortExprContext sortExpr() {
			return getRuleContext(SortExprContext.class,0);
		}
		public GTermContext gTerm() {
			return getRuleContext(GTermContext.class,0);
		}
		public LetBindingGTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letBindingGTerm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitLetBindingGTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetBindingGTermContext letBindingGTerm() throws RecognitionException {
		LetBindingGTermContext _localctx = new LetBindingGTermContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_letBindingGTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			match(T__0);
			setState(452);
			match(SYMBOL);
			setState(453);
			sortExpr();
			setState(454);
			gTerm();
			setState(455);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GTermStarContext extends ParserRuleContext {
		public GTermStarContext gTermStar() {
			return getRuleContext(GTermStarContext.class,0);
		}
		public GTermContext gTerm() {
			return getRuleContext(GTermContext.class,0);
		}
		public GTermStarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gTermStar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QSygusParserVisitor ) return ((QSygusParserVisitor<? extends T>)visitor).visitGTermStar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GTermStarContext gTermStar() throws RecognitionException {
		return gTermStar(0);
	}

	private GTermStarContext gTermStar(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		GTermStarContext _localctx = new GTermStarContext(_ctx, _parentState);
		GTermStarContext _prevctx = _localctx;
		int _startState = 92;
		enterRecursionRule(_localctx, 92, RULE_gTermStar, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(462);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new GTermStarContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_gTermStar);
					setState(458);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(459);
					gTerm();
					}
					} 
				}
				setState(464);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return weightPlus_sempred((WeightPlusContext)_localctx, predIndex);
		case 6:
			return cmdPlus_sempred((CmdPlusContext)_localctx, predIndex);
		case 17:
			return symbolPlus_sempred((SymbolPlusContext)_localctx, predIndex);
		case 20:
			return symbolPairPlus_sempred((SymbolPairPlusContext)_localctx, predIndex);
		case 24:
			return sortStar_sempred((SortStarContext)_localctx, predIndex);
		case 26:
			return symbolSortPairStar_sempred((SymbolSortPairStarContext)_localctx, predIndex);
		case 30:
			return letBindingTermPlus_sempred((LetBindingTermPlusContext)_localctx, predIndex);
		case 32:
			return termStar_sempred((TermStarContext)_localctx, predIndex);
		case 34:
			return ntDefPlus_sempred((NtDefPlusContext)_localctx, predIndex);
		case 36:
			return gTermPlus_sempred((GTermPlusContext)_localctx, predIndex);
		case 41:
			return literalPlus_sempred((LiteralPlusContext)_localctx, predIndex);
		case 44:
			return letBindingGTermPlus_sempred((LetBindingGTermPlusContext)_localctx, predIndex);
		case 46:
			return gTermStar_sempred((GTermStarContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean weightPlus_sempred(WeightPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean cmdPlus_sempred(CmdPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean symbolPlus_sempred(SymbolPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean symbolPairPlus_sempred(SymbolPairPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean sortStar_sempred(SortStarContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean symbolSortPairStar_sempred(SymbolSortPairStarContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean letBindingTermPlus_sempred(LetBindingTermPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean termStar_sempred(TermStarContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean ntDefPlus_sempred(NtDefPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean gTermPlus_sempred(GTermPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean literalPlus_sempred(LiteralPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean letBindingGTermPlus_sempred(LetBindingGTermPlusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean gTermStar_sempred(GTermStarContext _localctx, int predIndex) {
		switch (predIndex) {
		case 12:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3(\u01d4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5w\n\5\f\5\16\5z"+
		"\13\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\b\u0088\n\b\f"+
		"\b\16\b\u008b\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0098"+
		"\n\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a5\n\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00cb\n\17\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\7\23\u00dc\n\23\f\23"+
		"\16\23\u00df\13\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\7\26\u00ef\n\26\f\26\16\26\u00f2\13\26\3\27\3\27\3"+
		"\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\7\32\u010d\n\32\f\32\16\32"+
		"\u0110\13\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\7\34\u0119\n\34\f\34\16"+
		"\34\u011c\13\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\5\36\u012b\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 "+
		"\3 \3 \3 \3 \7 \u013a\n \f \16 \u013d\13 \3!\3!\3!\3!\3!\3!\3\"\3\"\3"+
		"\"\7\"\u0148\n\"\f\"\16\"\u014b\13\"\3#\3#\3#\3#\3#\5#\u0152\n#\3$\3$"+
		"\3$\3$\3$\7$\u0159\n$\f$\16$\u015c\13$\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3"+
		"&\3&\3&\7&\u016b\n&\f&\16&\u016e\13&\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3"+
		")\3)\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\5*\u018a\n*\3+\3+\3"+
		"+\3+\3+\7+\u0191\n+\f+\16+\u0194\13+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,"+
		"\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\5,\u01b2\n,\3-\3-"+
		"\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\7.\u01c1\n.\f.\16.\u01c4\13.\3/\3/\3"+
		"/\3/\3/\3/\3\60\3\60\3\60\7\60\u01cf\n\60\f\60\16\60\u01d2\13\60\3\60"+
		"\2\17\b\16$*\62\66>BFJTZ^\61\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^\2\4\3\2\6\b\3\2\24\25\u01d1\2"+
		"`\3\2\2\2\4b\3\2\2\2\6e\3\2\2\2\bj\3\2\2\2\n{\3\2\2\2\f}\3\2\2\2\16\u0082"+
		"\3\2\2\2\20\u0097\3\2\2\2\22\u0099\3\2\2\2\24\u00a4\3\2\2\2\26\u00a6\3"+
		"\2\2\2\30\u00ab\3\2\2\2\32\u00b1\3\2\2\2\34\u00ca\3\2\2\2\36\u00cc\3\2"+
		"\2\2 \u00ce\3\2\2\2\"\u00d2\3\2\2\2$\u00d6\3\2\2\2&\u00e0\3\2\2\2(\u00e5"+
		"\3\2\2\2*\u00e9\3\2\2\2,\u00f3\3\2\2\2.\u00f8\3\2\2\2\60\u0100\3\2\2\2"+
		"\62\u0109\3\2\2\2\64\u0111\3\2\2\2\66\u0115\3\2\2\28\u011d\3\2\2\2:\u012a"+
		"\3\2\2\2<\u012c\3\2\2\2>\u0134\3\2\2\2@\u013e\3\2\2\2B\u0144\3\2\2\2D"+
		"\u0151\3\2\2\2F\u0153\3\2\2\2H\u015d\3\2\2\2J\u0165\3\2\2\2L\u016f\3\2"+
		"\2\2N\u0173\3\2\2\2P\u0178\3\2\2\2R\u0189\3\2\2\2T\u018b\3\2\2\2V\u01b1"+
		"\3\2\2\2X\u01b3\3\2\2\2Z\u01bb\3\2\2\2\\\u01c5\3\2\2\2^\u01cb\3\2\2\2"+
		"`a\5\4\3\2a\3\3\2\2\2bc\5\f\7\2cd\5\16\b\2d\5\3\2\2\2ef\7\3\2\2fg\7\4"+
		"\2\2gh\7\'\2\2hi\7\5\2\2i\7\3\2\2\2jk\b\5\1\2kl\7\3\2\2lm\7\'\2\2mn\5"+
		"\n\6\2no\7\5\2\2ox\3\2\2\2pq\f\4\2\2qr\7\3\2\2rs\7\'\2\2st\5\n\6\2tu\7"+
		"\5\2\2uw\3\2\2\2vp\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\t\3\2\2\2zx"+
		"\3\2\2\2{|\t\2\2\2|\13\3\2\2\2}~\7\3\2\2~\177\7\t\2\2\177\u0080\5\b\5"+
		"\2\u0080\u0081\7\5\2\2\u0081\r\3\2\2\2\u0082\u0083\b\b\1\2\u0083\u0084"+
		"\5\20\t\2\u0084\u0089\3\2\2\2\u0085\u0086\f\4\2\2\u0086\u0088\5\20\t\2"+
		"\u0087\u0085\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a"+
		"\3\2\2\2\u008a\17\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u0098\5\6\4\2\u008d"+
		"\u0098\5.\30\2\u008e\u0098\5\60\31\2\u008f\u0098\5P)\2\u0090\u0098\5L"+
		"\'\2\u0091\u0098\5N(\2\u0092\u0098\5\32\16\2\u0093\u0098\5&\24\2\u0094"+
		"\u0098\5\26\f\2\u0095\u0098\5\22\n\2\u0096\u0098\5\30\r\2\u0097\u008c"+
		"\3\2\2\2\u0097\u008d\3\2\2\2\u0097\u008e\3\2\2\2\u0097\u008f\3\2\2\2\u0097"+
		"\u0090\3\2\2\2\u0097\u0091\3\2\2\2\u0097\u0092\3\2\2\2\u0097\u0093\3\2"+
		"\2\2\u0097\u0094\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2\2\2\u0098"+
		"\21\3\2\2\2\u0099\u009a\7\3\2\2\u009a\u009b\7\n\2\2\u009b\u009c\5\24\13"+
		"\2\u009c\u009d\7\5\2\2\u009d\23\3\2\2\2\u009e\u009f\7\3\2\2\u009f\u00a0"+
		"\7\'\2\2\u00a0\u00a1\5$\23\2\u00a1\u00a2\7\5\2\2\u00a2\u00a5\3\2\2\2\u00a3"+
		"\u00a5\7\'\2\2\u00a4\u009e\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5\25\3\2\2"+
		"\2\u00a6\u00a7\7\3\2\2\u00a7\u00a8\7\13\2\2\u00a8\u00a9\5:\36\2\u00a9"+
		"\u00aa\7\5\2\2\u00aa\27\3\2\2\2\u00ab\u00ac\7\3\2\2\u00ac\u00ad\7\f\2"+
		"\2\u00ad\u00ae\7\'\2\2\u00ae\u00af\5\34\17\2\u00af\u00b0\7\5\2\2\u00b0"+
		"\31\3\2\2\2\u00b1\u00b2\7\3\2\2\u00b2\u00b3\7\r\2\2\u00b3\u00b4\7\'\2"+
		"\2\u00b4\u00b5\5\34\17\2\u00b5\u00b6\7\5\2\2\u00b6\33\3\2\2\2\u00b7\u00b8"+
		"\7\3\2\2\u00b8\u00b9\7\16\2\2\u00b9\u00ba\7$\2\2\u00ba\u00cb\7\5\2\2\u00bb"+
		"\u00cb\7\17\2\2\u00bc\u00cb\7\20\2\2\u00bd\u00cb\7\21\2\2\u00be\u00bf"+
		"\7\3\2\2\u00bf\u00c0\7\22\2\2\u00c0\u00c1\5\"\22\2\u00c1\u00c2\7\5\2\2"+
		"\u00c2\u00cb\3\2\2\2\u00c3\u00c4\7\3\2\2\u00c4\u00c5\7\23\2\2\u00c5\u00c6"+
		"\5\34\17\2\u00c6\u00c7\5\34\17\2\u00c7\u00c8\7\5\2\2\u00c8\u00cb\3\2\2"+
		"\2\u00c9\u00cb\7\'\2\2\u00ca\u00b7\3\2\2\2\u00ca\u00bb\3\2\2\2\u00ca\u00bc"+
		"\3\2\2\2\u00ca\u00bd\3\2\2\2\u00ca\u00be\3\2\2\2\u00ca\u00c3\3\2\2\2\u00ca"+
		"\u00c9\3\2\2\2\u00cb\35\3\2\2\2\u00cc\u00cd\t\3\2\2\u00cd\37\3\2\2\2\u00ce"+
		"\u00cf\7\'\2\2\u00cf\u00d0\7\26\2\2\u00d0\u00d1\7\'\2\2\u00d1!\3\2\2\2"+
		"\u00d2\u00d3\7\3\2\2\u00d3\u00d4\5$\23\2\u00d4\u00d5\7\5\2\2\u00d5#\3"+
		"\2\2\2\u00d6\u00d7\b\23\1\2\u00d7\u00d8\7\'\2\2\u00d8\u00dd\3\2\2\2\u00d9"+
		"\u00da\f\4\2\2\u00da\u00dc\7\'\2\2\u00db\u00d9\3\2\2\2\u00dc\u00df\3\2"+
		"\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de%\3\2\2\2\u00df\u00dd"+
		"\3\2\2\2\u00e0\u00e1\7\3\2\2\u00e1\u00e2\7\27\2\2\u00e2\u00e3\5(\25\2"+
		"\u00e3\u00e4\7\5\2\2\u00e4\'\3\2\2\2\u00e5\u00e6\7\3\2\2\u00e6\u00e7\5"+
		"*\26\2\u00e7\u00e8\7\5\2\2\u00e8)\3\2\2\2\u00e9\u00ea\b\26\1\2\u00ea\u00eb"+
		"\5,\27\2\u00eb\u00f0\3\2\2\2\u00ec\u00ed\f\4\2\2\u00ed\u00ef\5,\27\2\u00ee"+
		"\u00ec\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1+\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\7\3\2\2\u00f4\u00f5"+
		"\7\'\2\2\u00f5\u00f6\7(\2\2\u00f6\u00f7\7\5\2\2\u00f7-\3\2\2\2\u00f8\u00f9"+
		"\7\3\2\2\u00f9\u00fa\7\30\2\2\u00fa\u00fb\7\'\2\2\u00fb\u00fc\5\64\33"+
		"\2\u00fc\u00fd\5\34\17\2\u00fd\u00fe\5:\36\2\u00fe\u00ff\7\5\2\2\u00ff"+
		"/\3\2\2\2\u0100\u0101\7\3\2\2\u0101\u0102\7\31\2\2\u0102\u0103\7\'\2\2"+
		"\u0103\u0104\7\3\2\2\u0104\u0105\5\62\32\2\u0105\u0106\7\5\2\2\u0106\u0107"+
		"\5\34\17\2\u0107\u0108\7\5\2\2\u0108\61\3\2\2\2\u0109\u010e\b\32\1\2\u010a"+
		"\u010b\f\4\2\2\u010b\u010d\5\34\17\2\u010c\u010a\3\2\2\2\u010d\u0110\3"+
		"\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\63\3\2\2\2\u0110"+
		"\u010e\3\2\2\2\u0111\u0112\7\3\2\2\u0112\u0113\5\66\34\2\u0113\u0114\7"+
		"\5\2\2\u0114\65\3\2\2\2\u0115\u011a\b\34\1\2\u0116\u0117\f\4\2\2\u0117"+
		"\u0119\58\35\2\u0118\u0116\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2"+
		"\2\2\u011a\u011b\3\2\2\2\u011b\67\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u011e"+
		"\7\3\2\2\u011e\u011f\7\'\2\2\u011f\u0120\5\34\17\2\u0120\u0121\7\5\2\2"+
		"\u01219\3\2\2\2\u0122\u0123\7\3\2\2\u0123\u0124\7\'\2\2\u0124\u0125\5"+
		"B\"\2\u0125\u0126\7\5\2\2\u0126\u012b\3\2\2\2\u0127\u012b\5D#\2\u0128"+
		"\u012b\7\'\2\2\u0129\u012b\5<\37\2\u012a\u0122\3\2\2\2\u012a\u0127\3\2"+
		"\2\2\u012a\u0128\3\2\2\2\u012a\u0129\3\2\2\2\u012b;\3\2\2\2\u012c\u012d"+
		"\7\3\2\2\u012d\u012e\7\32\2\2\u012e\u012f\7\3\2\2\u012f\u0130\5> \2\u0130"+
		"\u0131\7\5\2\2\u0131\u0132\5:\36\2\u0132\u0133\7\5\2\2\u0133=\3\2\2\2"+
		"\u0134\u0135\b \1\2\u0135\u0136\5@!\2\u0136\u013b\3\2\2\2\u0137\u0138"+
		"\f\4\2\2\u0138\u013a\5@!\2\u0139\u0137\3\2\2\2\u013a\u013d\3\2\2\2\u013b"+
		"\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c?\3\2\2\2\u013d\u013b\3\2\2\2"+
		"\u013e\u013f\7\3\2\2\u013f\u0140\7\'\2\2\u0140\u0141\5\34\17\2\u0141\u0142"+
		"\5:\36\2\u0142\u0143\7\5\2\2\u0143A\3\2\2\2\u0144\u0149\b\"\1\2\u0145"+
		"\u0146\f\4\2\2\u0146\u0148\5:\36\2\u0147\u0145\3\2\2\2\u0148\u014b\3\2"+
		"\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014aC\3\2\2\2\u014b\u0149"+
		"\3\2\2\2\u014c\u0152\7$\2\2\u014d\u0152\5\36\20\2\u014e\u0152\7%\2\2\u014f"+
		"\u0152\5 \21\2\u0150\u0152\7&\2\2\u0151\u014c\3\2\2\2\u0151\u014d\3\2"+
		"\2\2\u0151\u014e\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0150\3\2\2\2\u0152"+
		"E\3\2\2\2\u0153\u0154\b$\1\2\u0154\u0155\5H%\2\u0155\u015a\3\2\2\2\u0156"+
		"\u0157\f\4\2\2\u0157\u0159\5H%\2\u0158\u0156\3\2\2\2\u0159\u015c\3\2\2"+
		"\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015bG\3\2\2\2\u015c\u015a"+
		"\3\2\2\2\u015d\u015e\7\3\2\2\u015e\u015f\7\'\2\2\u015f\u0160\5\34\17\2"+
		"\u0160\u0161\7\3\2\2\u0161\u0162\5J&\2\u0162\u0163\7\5\2\2\u0163\u0164"+
		"\7\5\2\2\u0164I\3\2\2\2\u0165\u0166\b&\1\2\u0166\u0167\5R*\2\u0167\u016c"+
		"\3\2\2\2\u0168\u0169\f\4\2\2\u0169\u016b\5R*\2\u016a\u0168\3\2\2\2\u016b"+
		"\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016dK\3\2\2\2"+
		"\u016e\u016c\3\2\2\2\u016f\u0170\7\3\2\2\u0170\u0171\7\33\2\2\u0171\u0172"+
		"\7\5\2\2\u0172M\3\2\2\2\u0173\u0174\7\3\2\2\u0174\u0175\7\34\2\2\u0175"+
		"\u0176\5:\36\2\u0176\u0177\7\5\2\2\u0177O\3\2\2\2\u0178\u0179\7\3\2\2"+
		"\u0179\u017a\7\35\2\2\u017a\u017b\7\'\2\2\u017b\u017c\5\64\33\2\u017c"+
		"\u017d\5\34\17\2\u017d\u017e\7\3\2\2\u017e\u017f\5F$\2\u017f\u0180\7\5"+
		"\2\2\u0180\u0181\7\5\2\2\u0181Q\3\2\2\2\u0182\u0183\7\3\2\2\u0183\u0184"+
		"\5V,\2\u0184\u0185\7\36\2\2\u0185\u0186\5T+\2\u0186\u0187\7\5\2\2\u0187"+
		"\u018a\3\2\2\2\u0188\u018a\5V,\2\u0189\u0182\3\2\2\2\u0189\u0188\3\2\2"+
		"\2\u018aS\3\2\2\2\u018b\u018c\b+\1\2\u018c\u018d\5D#\2\u018d\u0192\3\2"+
		"\2\2\u018e\u018f\f\4\2\2\u018f\u0191\5D#\2\u0190\u018e\3\2\2\2\u0191\u0194"+
		"\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193U\3\2\2\2\u0194"+
		"\u0192\3\2\2\2\u0195\u01b2\7\'\2\2\u0196\u01b2\5D#\2\u0197\u0198\7\3\2"+
		"\2\u0198\u0199\7\'\2\2\u0199\u019a\5^\60\2\u019a\u019b\7\5\2\2\u019b\u01b2"+
		"\3\2\2\2\u019c\u019d\7\3\2\2\u019d\u019e\7\37\2\2\u019e\u019f\5\34\17"+
		"\2\u019f\u01a0\7\5\2\2\u01a0\u01b2\3\2\2\2\u01a1\u01a2\7\3\2\2\u01a2\u01a3"+
		"\7 \2\2\u01a3\u01a4\5\34\17\2\u01a4\u01a5\7\5\2\2\u01a5\u01b2\3\2\2\2"+
		"\u01a6\u01a7\7\3\2\2\u01a7\u01a8\7!\2\2\u01a8\u01a9\5\34\17\2\u01a9\u01aa"+
		"\7\5\2\2\u01aa\u01b2\3\2\2\2\u01ab\u01ac\7\3\2\2\u01ac\u01ad\7\"\2\2\u01ad"+
		"\u01ae\5\34\17\2\u01ae\u01af\7\5\2\2\u01af\u01b2\3\2\2\2\u01b0\u01b2\5"+
		"X-\2\u01b1\u0195\3\2\2\2\u01b1\u0196\3\2\2\2\u01b1\u0197\3\2\2\2\u01b1"+
		"\u019c\3\2\2\2\u01b1\u01a1\3\2\2\2\u01b1\u01a6\3\2\2\2\u01b1\u01ab\3\2"+
		"\2\2\u01b1\u01b0\3\2\2\2\u01b2W\3\2\2\2\u01b3\u01b4\7\3\2\2\u01b4\u01b5"+
		"\7\32\2\2\u01b5\u01b6\7\3\2\2\u01b6\u01b7\5Z.\2\u01b7\u01b8\7\5\2\2\u01b8"+
		"\u01b9\5V,\2\u01b9\u01ba\7\5\2\2\u01baY\3\2\2\2\u01bb\u01bc\b.\1\2\u01bc"+
		"\u01bd\5\\/\2\u01bd\u01c2\3\2\2\2\u01be\u01bf\f\4\2\2\u01bf\u01c1\5\\"+
		"/\2\u01c0\u01be\3\2\2\2\u01c1\u01c4\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c2"+
		"\u01c3\3\2\2\2\u01c3[\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c5\u01c6\7\3\2\2"+
		"\u01c6\u01c7\7\'\2\2\u01c7\u01c8\5\34\17\2\u01c8\u01c9\5V,\2\u01c9\u01ca"+
		"\7\5\2\2\u01ca]\3\2\2\2\u01cb\u01d0\b\60\1\2\u01cc\u01cd\f\4\2\2\u01cd"+
		"\u01cf\5V,\2\u01ce\u01cc\3\2\2\2\u01cf\u01d2\3\2\2\2\u01d0\u01ce\3\2\2"+
		"\2\u01d0\u01d1\3\2\2\2\u01d1_\3\2\2\2\u01d2\u01d0\3\2\2\2\26x\u0089\u0097"+
		"\u00a4\u00ca\u00dd\u00f0\u010e\u011a\u012a\u013b\u0149\u0151\u015a\u016c"+
		"\u0189\u0192\u01b1\u01c2\u01d0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}