/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2009 Frederico Caldeira Knabben
 *
 * == BEGIN LICENSE ==
 *
 * Licensed under the terms of any of the following licenses at your
 * choice:
 *
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 *
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 *
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 *
 * == END LICENSE ==
 *
 * Japanese language file.
 */

var FCKLang =
{
// Language direction : "ltr" (left to right) or "rtl" (right to left).
Dir					: "ltr",

ToolbarCollapse		: "ツールバーを隠す",
ToolbarExpand		: "ツールバーを表示",

// Toolbar Items and Context Menu
Save				: "�存",
NewPage				: "新しいページ",
Preview				: "プレビュー",
Cut					: "切り取り",
Copy				: "コピー",
Paste				: "貼り�け",
PasteText			: "プレーンテキスト貼り�け",
PasteWord			: "ワード文章から貼り�け",
Print				: "印刷",
SelectAll			: "すべて選択",
RemoveFormat		: "フォーマット削除",
InsertLinkLbl		: "リンク",
InsertLink			: "リンク�入/編集",
RemoveLink			: "リンク削除",
VisitLink			: "リンクを開く",
Anchor				: "アンカー�入/編集",
AnchorDelete		: "アンカー削除",
InsertImageLbl		: "イメージ",
InsertImage			: "イメージ�入/編集",
InsertFlashLbl		: "Flash",
InsertFlash			: "Flash�入/編集",
InsertTableLbl		: "テーブル",
InsertTable			: "テーブル�入/編集",
InsertLineLbl		: "ライン",
InsertLine			: "横罫線",
InsertSpecialCharLbl: "特殊文字",
InsertSpecialChar	: "特殊文字�入",
InsertSmileyLbl		: "絵文字",
InsertSmiley		: "絵文字�入",
About				: "FCKeditorヘルプ",
Bold				: "太字",
Italic				: "斜体",
Underline			: "下線",
StrikeThrough		: "打ち消し線",
Subscript			: "�え字",
Superscript			: "上�き文字",
LeftJustify			: "左揃え",
CenterJustify		: "中央揃え",
RightJustify		: "右揃え",
BlockJustify		: "両端揃え",
DecreaseIndent		: "インデント解除",
IncreaseIndent		: "インデント",
Blockquote			: "ブロック引用",
CreateDiv			: "Div 作成",
EditDiv				: "Div 編集",
DeleteDiv			: "Div 削除",
Undo				: "元に�す",
Redo				: "やり直し",
NumberedListLbl		: "段落番号",
NumberedList		: "段落番号の�加/削除",
BulletedListLbl		: "箇条書き",
BulletedList		: "箇条書きの�加/削除",
ShowTableBorders	: "テーブルボーダー表示",
ShowDetails			: "詳細表示",
Style				: "ス�イル",
FontFormat			: "フォーマット",
Font				: "フォント",
FontSize			: "サイズ",
TextColor			: "テキスト色",
BGColor				: "背景色",
Source				: "ソース",
Find				: "検索",
Replace				: "置き換え",
SpellCheck			: "スペルチェック",
UniversalKeyboard	: "ユニバーサル�キーボード",
PageBreakLbl		: "改ページ",
PageBreak			: "改ページ�入",

Form			: "フォーム",
Checkbox		: "チェックボックス",
RadioButton		: "ラジオボ�ン",
TextField		: "��行テキスト",
Textarea		: "テキストエリア",
HiddenField		: "不可視フィールド",
Button			: "ボ�ン",
SelectionField	: "選択フィールド",
ImageButton		: "�像ボ�ン",

FitWindow		: "エディ�サイズを最大にします",
ShowBlocks		: "ブロック表示",

// Context Menu
EditLink			: "リンク編集",
CellCM				: "�ル",
RowCM				: "行",
ColumnCM			: "カラム",
InsertRowAfter		: "列の後に�入",
InsertRowBefore		: "列の前に�入",
DeleteRows			: "行削除",
InsertColumnAfter	: "カラムの後に�入",
InsertColumnBefore	: "カラムの前に�入",
DeleteColumns		: "列削除",
InsertCellAfter		: "�ルの後に�入",
InsertCellBefore	: "�ルの前に�入",
DeleteCells			: "�ル削除",
MergeCells			: "�ル結合",
MergeRight			: "右に結合",
MergeDown			: "下に結合",
HorizontalSplitCell	: "�ルを水平方向分割",
VerticalSplitCell	: "�ルを垂直方向に分割",
TableDelete			: "テーブル削除",
CellProperties		: "�ル プロパティ",
TableProperties		: "テーブル プロパティ",
ImageProperties		: "イメージ プロパティ",
FlashProperties		: "Flash プロパティ",

AnchorProp			: "アンカー プロパティ",
ButtonProp			: "ボ�ン プロパティ",
CheckboxProp		: "チェックボックス プロパティ",
HiddenFieldProp		: "不可視フィールド プロパティ",
RadioButtonProp		: "ラジオボ�ン プロパティ",
ImageButtonProp		: "�像ボ�ン プロパティ",
TextFieldProp		: "��行テキスト プロパティ",
SelectionFieldProp	: "選択フィールド プロパティ",
TextareaProp		: "テキストエリア プロパティ",
FormProp			: "フォーム プロパティ",

FontFormats			: "標準;書式�き;アドレス;見出し 1;見出し 2;見出し 3;見出し 4;見出し 5;見出し 6;標準 (DIV)",

// Alerts and Messages
ProcessingXHTML		: "XHTML処理中. しばらくお待ちください...",
Done				: "完了",
PasteWordConfirm	: "貼り�けを行うテキストは、ワード文章からコピーされようとしています。貼り�ける前にクリーニングを行いますか��",
NotCompatiblePaste	: "このコマンドはイン�ーネット�エクスプローラーバージョン5.5�上で利用可能です。クリーニングしないで貼り付けを行いますか��",
UnknownToolbarItem	: "未知のツールバー項目 \"%1\"",
UnknownCommand		: "未知のコマンド名 \"%1\"",
NotImplemented		: "コマンドはインプリメントされませんでした。",
UnknownToolbarSet	: "ツールバー設定 \"%1\" 存在しません。",
NoActiveX			: "エラー、警告メッ�ージなどが発生した場合、ブラウザーの�キュリティ設定によりエディ�のいくつかの機能が制限されている可能性があります。セキュリティ設定のオプションで\"ActiveXコントロールとプラグインの実行\"を有効にするにしてください。",
BrowseServerBlocked : "サーバーブラウザーを開くことができませんでした。ポップアップ�ブロック機能が無効になっているか確認してください。",
DialogBlocked		: "ダイアログウィンドウを開くことができませんでした。ポップアップ�ブロック機能が無効になっているか確認してください。",
VisitLinkBlocked	: "新しいウィンドウを開くことができませんでした。ポップアップ�ブロック機能が無効になっているか確認してください。",

// Dialogs
DlgBtnOK			: "OK",
DlgBtnCancel		: "キャン�ル",
DlgBtnClose			: "閉じる",
DlgBtnBrowseServer	: "サーバーブラウザー",
DlgAdvancedTag		: "高度な設定",
DlgOpOther			: "<その�>",
DlgInfoTab			: "情報",
DlgAlertUrl			: "URLを�入してください",

// General Dialogs Labels
DlgGenNotSet		: "<なし>",
DlgGenId			: "Id",
DlgGenLangDir		: "文字表記の方向",
DlgGenLangDirLtr	: "左から右 (LTR)",
DlgGenLangDirRtl	: "右から左 (RTL)",
DlgGenLangCode		: "言語コード",
DlgGenAccessKey		: "アク�スキー",
DlgGenName			: "Name属性",
DlgGenTabIndex		: "�ブインデックス",
DlgGenLongDescr		: "longdesc属性(長文説明)",
DlgGenClass			: "ス�イルシートクラス",
DlgGenTitle			: "Title属性",
DlgGenContType		: "Content Type属性",
DlgGenLinkCharset	: "リンクcharset属性",
DlgGenStyle			: "ス�イルシート",

// Image Dialog
DlgImgTitle			: "イメージ プロパティ",
DlgImgInfoTab		: "イメージ 情報",
DlgImgBtnUpload		: "サーバーに送�",
DlgImgURL			: "URL",
DlgImgUpload		: "アップロード",
DlgImgAlt			: "��テキスト",
DlgImgWidth			: "幅",
DlgImgHeight		: "高さ",
DlgImgLockRatio		: "ロック比率",
DlgBtnResetSize		: "サイズリ�ット",
DlgImgBorder		: "ボーダー",
DlgImgHSpace		: "横間隔",
DlgImgVSpace		: "縦間隔",
DlgImgAlign			: "行揃え",
DlgImgAlignLeft		: "左",
DlgImgAlignAbsBottom: "下部(絶対的)",
DlgImgAlignAbsMiddle: "中央(絶対的)",
DlgImgAlignBaseline	: "ベースライン",
DlgImgAlignBottom	: "下",
DlgImgAlignMiddle	: "中央",
DlgImgAlignRight	: "右",
DlgImgAlignTextTop	: "テキスト上部",
DlgImgAlignTop		: "上",
DlgImgPreview		: "プレビュー",
DlgImgAlertUrl		: "イメージのURLを入力してください。",
DlgImgLinkTab		: "リンク",

// Flash Dialog
DlgFlashTitle		: "Flash プロパティ",
DlgFlashChkPlay		: "再生",
DlgFlashChkLoop		: "ループ再生",
DlgFlashChkMenu		: "Flashメニュー可能",
DlgFlashScale		: "拡大縮小設定",
DlgFlashScaleAll	: "すべて表示",
DlgFlashScaleNoBorder	: "外が見えない様に拡大",
DlgFlashScaleFit	: "上下左右にフィット",

// Link Dialog
DlgLnkWindowTitle	: "ハイパーリンク",
DlgLnkInfoTab		: "ハイパーリンク 情報",
DlgLnkTargetTab		: "�ーゲット",

DlgLnkType			: "リンク�イプ",
DlgLnkTypeURL		: "URL",
DlgLnkTypeAnchor	: "このページのアンカー",
DlgLnkTypeEMail		: "E-Mail",
DlgLnkProto			: "プロトコル",
DlgLnkProtoOther	: "<その�>",
DlgLnkURL			: "URL",
DlgLnkAnchorSel		: "アンカーを選択",
DlgLnkAnchorByName	: "アンカー名",
DlgLnkAnchorById	: "エレメントID",
DlgLnkNoAnchors		: "(ドキュメントにおいて利用可能なアンカーはありません。)",
DlgLnkEMail			: "E-Mail アドレス",
DlgLnkEMailSubject	: "�名",
DlgLnkEMailBody		: "本文",
DlgLnkUpload		: "アップロード",
DlgLnkBtnUpload		: "サーバーに送�",

DlgLnkTarget		: "�ーゲット",
DlgLnkTargetFrame	: "<フレーム>",
DlgLnkTargetPopup	: "<ポップアップウィンドウ>",
DlgLnkTargetBlank	: "新しいウィンドウ (_blank)",
DlgLnkTargetParent	: "親ウィンドウ (_parent)",
DlgLnkTargetSelf	: "同じウィンドウ (_self)",
DlgLnkTargetTop		: "最上位ウィンドウ (_top)",
DlgLnkTargetFrameName	: "目的のフレーム名",
DlgLnkPopWinName	: "ポップアップウィンドウ名",
DlgLnkPopWinFeat	: "ポップアップウィンドウ特徴",
DlgLnkPopResize		: "リサイズ可能",
DlgLnkPopLocation	: "ロケーションバー",
DlgLnkPopMenu		: "メニューバー",
DlgLnkPopScroll		: "スクロールバー",
DlgLnkPopStatus		: "ステー�スバー",
DlgLnkPopToolbar	: "ツールバー",
DlgLnkPopFullScrn	: "全�面モード(IE)",
DlgLnkPopDependent	: "開いたウィンドウに連動して閉じる (Netscape)",
DlgLnkPopWidth		: "幅",
DlgLnkPopHeight		: "高さ",
DlgLnkPopLeft		: "左端からの座標で指定",
DlgLnkPopTop		: "上端からの座標で指定",

DlnLnkMsgNoUrl		: "リンクURLを入力してください。",
DlnLnkMsgNoEMail	: "メールアドレスを入力してください。",
DlnLnkMsgNoAnchor	: "アンカーを選択してください。",
DlnLnkMsgInvPopName	: "ポップ�アップ名は英字で始まる文字で指定してくだい。ポップ�アップ名にスペースは含めません",

// Color Dialog
DlgColorTitle		: "色選択",
DlgColorBtnClear	: "クリア",
DlgColorHighlight	: "ハイライト",
DlgColorSelected	: "選択色",

// Smiley Dialog
DlgSmileyTitle		: "顔文字�入",

// Special Character Dialog
DlgSpecialCharTitle	: "特殊文字選択",

// Table Dialog
DlgTableTitle		: "テーブル プロパティ",
DlgTableRows		: "行",
DlgTableColumns		: "列",
DlgTableBorder		: "ボーダーサイズ",
DlgTableAlign		: "キャプションの整列",
DlgTableAlignNotSet	: "<なし>",
DlgTableAlignLeft	: "左",
DlgTableAlignCenter	: "中央",
DlgTableAlignRight	: "右",
DlgTableWidth		: "テーブル幅",
DlgTableWidthPx		: "ピク�ル",
DlgTableWidthPc		: "パー�ント",
DlgTableHeight		: "テーブル高さ",
DlgTableCellSpace	: "�ル内余白",
DlgTableCellPad		: "�ル内間隔",
DlgTableCaption		: "����ﾌﾟｼｮﾝ",
DlgTableSummary		: "テーブル目的/構造",
DlgTableHeaders		: "Headers",	//MISSING
DlgTableHeadersNone		: "None",	//MISSING
DlgTableHeadersColumn	: "First column",	//MISSING
DlgTableHeadersRow		: "First Row",	//MISSING
DlgTableHeadersBoth		: "Both",	//MISSING

// Table Cell Dialog
DlgCellTitle		: "�ル プロパティ",
DlgCellWidth		: "幅",
DlgCellWidthPx		: "ピク�ル",
DlgCellWidthPc		: "パー�ント",
DlgCellHeight		: "高さ",
DlgCellWordWrap		: "折り�し",
DlgCellWordWrapNotSet	: "<なし>",
DlgCellWordWrapYes	: "Yes",
DlgCellWordWrapNo	: "No",
DlgCellHorAlign		: "�ル横の整列",
DlgCellHorAlignNotSet	: "<なし>",
DlgCellHorAlignLeft	: "左",
DlgCellHorAlignCenter	: "中央",
DlgCellHorAlignRight: "右",
DlgCellVerAlign		: "�ル縦の整列",
DlgCellVerAlignNotSet	: "<なし>",
DlgCellVerAlignTop	: "上",
DlgCellVerAlignMiddle	: "中央",
DlgCellVerAlignBottom	: "下",
DlgCellVerAlignBaseline	: "ベースライン",
DlgCellType		: "Cell Type",	//MISSING
DlgCellTypeData		: "Data",	//MISSING
DlgCellTypeHeader	: "Header",	//MISSING
DlgCellRowSpan		: "縦幅(行数)",
DlgCellCollSpan		: "横幅(列数)",
DlgCellBackColor	: "背景色",
DlgCellBorderColor	: "ボーダーカラー",
DlgCellBtnSelect	: "選択...",

// Find and Replace Dialog
DlgFindAndReplaceTitle	: "検索して置換",

// Find Dialog
DlgFindTitle		: "検索",
DlgFindFindBtn		: "検索",
DlgFindNotFoundMsg	: "指定された文字列は見つかりませんでした。",

// Replace Dialog
DlgReplaceTitle			: "置き換え",
DlgReplaceFindLbl		: "検索する文字列:",
DlgReplaceReplaceLbl	: "置換えする文字列:",
DlgReplaceCaseChk		: "部分一致",
DlgReplaceReplaceBtn	: "置換え",
DlgReplaceReplAllBtn	: "すべて置換え",
DlgReplaceWordChk		: "単語単位で一致",

// Paste Operations / Dialog
PasteErrorCut	: "ブラウザーの�キュリティ設定によりエディ�の切り取り操作が自動で実行することができません。実行するには手動でキーボードの(Ctrl+X)を�用してください。",
PasteErrorCopy	: "ブラウザーの�キュリティ設定によりエディ�のコピー操作が自動で実行することができません。実行するには手動でキーボードの(Ctrl+C)を�用してください。",

PasteAsText		: "プレーンテキスト貼り�け",
PasteFromWord	: "ワード文章から貼り�け",

DlgPasteMsg2	: "キーボード(<STRONG>Ctrl+V</STRONG>)を�用して、次の入力エリア内で貼って、<STRONG>OK</STRONG>を押してください。",
DlgPasteSec		: "ブラウザの�キュリティ設定により、エディ�はクリップボード�デー�に直接アクセスすることができません。このウィンドウは貼り付け操作を行う度に表示されます。",
DlgPasteIgnoreFont		: "Font�グのFace属性を無視します。",
DlgPasteRemoveStyles	: "ス�イル定義を削除します。",

// Color Picker
ColorAutomatic	: "自動",
ColorMoreColors	: "その�の色...",

// Document Properties
DocProps		: "文書 プロパティ",

// Anchor Dialog
DlgAnchorTitle		: "アンカー プロパティ",
DlgAnchorName		: "アンカー名",
DlgAnchorErrorName	: "アンカー名を�ず入力してください。",

// Speller Pages Dialog
DlgSpellNotInDic		: "辞書にありません",
DlgSpellChangeTo		: "変更",
DlgSpellBtnIgnore		: "無視",
DlgSpellBtnIgnoreAll	: "すべて無視",
DlgSpellBtnReplace		: "置換",
DlgSpellBtnReplaceAll	: "すべて置換",
DlgSpellBtnUndo			: "やり直し",
DlgSpellNoSuggestions	: "- 該当なし -",
DlgSpellProgress		: "スペルチェック処理中...",
DlgSpellNoMispell		: "スペルチェック完了: スペルの誤りはありませんでした",
DlgSpellNoChanges		: "スペルチェック完了: 語句は変更されませんでした",
DlgSpellOneChange		: "スペルチェック完了: ��語句変更されました",
DlgSpellManyChanges		: "スペルチェック完了: %1 語句変更されました",

IeSpellDownload			: "スペルチェッカーがインストールされていません。�すぐダウンロードしますか?",

// Button Dialog
DlgButtonText		: "テキスト (値)",
DlgButtonType		: "�イプ",
DlgButtonTypeBtn	: "ボ�ン",
DlgButtonTypeSbm	: "送�",
DlgButtonTypeRst	: "リ�ット",

// Checkbox and Radio Button Dialogs
DlgCheckboxName		: "名前",
DlgCheckboxValue	: "値",
DlgCheckboxSelected	: "選択済�",

// Form Dialog
DlgFormName		: "フォーム名",
DlgFormAction	: "アクション",
DlgFormMethod	: "メソッド",

// Select Field Dialog
DlgSelectName		: "名前",
DlgSelectValue		: "値",
DlgSelectSize		: "サイズ",
DlgSelectLines		: "行",
DlgSelectChkMulti	: "複数項目選択を許可",
DlgSelectOpAvail	: "利用可能なオプション",
DlgSelectOpText		: "選択項目名",
DlgSelectOpValue	: "選択項目値",
DlgSelectBtnAdd		: "�加",
DlgSelectBtnModify	: "編集",
DlgSelectBtnUp		: "上へ",
DlgSelectBtnDown	: "下へ",
DlgSelectBtnSetValue : "選択した値を設定",
DlgSelectBtnDelete	: "削除",

// Textarea Dialog
DlgTextareaName	: "名前",
DlgTextareaCols	: "列",
DlgTextareaRows	: "行",

// Text Field Dialog
DlgTextName			: "名前",
DlgTextValue		: "値",
DlgTextCharWidth	: "サイズ",
DlgTextMaxChars		: "最大長",
DlgTextType			: "�イプ",
DlgTextTypeText		: "テキスト",
DlgTextTypePass		: "パスワード入力",

// Hidden Field Dialog
DlgHiddenName	: "名前",
DlgHiddenValue	: "値",

// Bulleted List Dialog
BulletedListProp	: "箇条書き プロパティ",
NumberedListProp	: "段落番号 プロパティ",
DlgLstStart			: "開始文字",
DlgLstType			: "�イプ",
DlgLstTypeCircle	: "白丸",
DlgLstTypeDisc		: "�丸",
DlgLstTypeSquare	: "四角",
DlgLstTypeNumbers	: "アラビア数字 (1, 2, 3)",
DlgLstTypeLCase		: "英字小文字 (a, b, c)",
DlgLstTypeUCase		: "英字大文字 (A, B, C)",
DlgLstTypeSRoman	: "ローマ数字小文字 (i, ii, iii)",
DlgLstTypeLRoman	: "ローマ数字大文字 (I, II, III)",

// Document Properties Dialog
DlgDocGeneralTab	: "全般",
DlgDocBackTab		: "背景",
DlgDocColorsTab		: "色とマージン",
DlgDocMetaTab		: "メ�デー�",

DlgDocPageTitle		: "ページ�イトル",
DlgDocLangDir		: "言語文字表記の方向",
DlgDocLangDirLTR	: "左から右に表記(LTR)",
DlgDocLangDirRTL	: "右から左に表記(RTL)",
DlgDocLangCode		: "言語コード",
DlgDocCharSet		: "文字�ット符号化",
DlgDocCharSetCE		: "Central European",
DlgDocCharSetCT		: "Chinese Traditional (Big5)",
DlgDocCharSetCR		: "Cyrillic",
DlgDocCharSetGR		: "Greek",
DlgDocCharSetJP		: "Japanese",
DlgDocCharSetKR		: "Korean",
DlgDocCharSetTR		: "Turkish",
DlgDocCharSetUN		: "Unicode (UTF-8)",
DlgDocCharSetWE		: "Western European",
DlgDocCharSetOther	: "�の文字�ット符号化",

DlgDocDocType		: "文書�イプヘッダー",
DlgDocDocTypeOther	: "その�文書�イプヘッダー",
DlgDocIncXHTML		: "XHTML宣言をインクルード",
DlgDocBgColor		: "背景色",
DlgDocBgImage		: "背景�像 URL",
DlgDocBgNoScroll	: "スクロールしない背景",
DlgDocCText			: "テキスト",
DlgDocCLink			: "リンク",
DlgDocCVisited		: "アク�ス済�リンク",
DlgDocCActive		: "アク�ス中リンク",
DlgDocMargins		: "ページ�マージン",
DlgDocMaTop			: "上部",
DlgDocMaLeft		: "左",
DlgDocMaRight		: "右",
DlgDocMaBottom		: "下部",
DlgDocMeIndex		: "文書のキーワード(カンマ区切り)",
DlgDocMeDescr		: "文書の概要",
DlgDocMeAuthor		: "文書の作者",
DlgDocMeCopy		: "文書の著作権",
DlgDocPreview		: "プレビュー",

// Templates Dialog
Templates			: "テンプレート(雛形)",
DlgTemplatesTitle	: "テンプレート内容",
DlgTemplatesSelMsg	: "エディ�ーで�用するテンプレートを選択してください。<br>(現在のエディタの内容は失われます):",
DlgTemplatesLoading	: "テンプレート一覧読�込�中. しばらくお待ちください...",
DlgTemplatesNoTpl	: "(テンプレートが定義されていません)",
DlgTemplatesReplace	: "現在のエディ�の内容と置換えをします",

// About Dialog
DlgAboutAboutTab	: "バージョン情報",
DlgAboutBrowserInfoTab	: "ブラウザ情報",
DlgAboutLicenseTab	: "ライ�ンス",
DlgAboutVersion		: "バージョン",
DlgAboutInfo		: "より詳しい情報はこちらで",

// Div Dialog
DlgDivGeneralTab	: "全般",
DlgDivAdvancedTab	: "高度な設定",
DlgDivStyle		: "ス�イル",
DlgDivInlineStyle	: "インラインス�イル",

ScaytTitle			: "SCAYT",	//MISSING
ScaytTitleOptions	: "Options",	//MISSING
ScaytTitleLangs		: "Languages",	//MISSING
ScaytTitleAbout		: "About"	//MISSING
};
