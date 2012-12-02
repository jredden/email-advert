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
 * Chinese Simplified language file.
 */

var FCKLang =
{
// Language direction : "ltr" (left to right) or "rtl" (right to left).
Dir					: "ltr",

ToolbarCollapse		: "折叠工具栏",
ToolbarExpand		: "展开工具栏",

// Toolbar Items and Context Menu
Save				: "�存",
NewPage				: "新�",
Preview				: "预览",
Cut					: "剪切",
Copy				: "复制",
Paste				: "粘贴",
PasteText			: "粘贴为无格式文本",
PasteWord			: "� MS Word 粘贴",
Print				: "打印",
SelectAll			: "全选",
RemoveFormat		: "清除格式",
InsertLinkLbl		: "超链接",
InsertLink			: "插入/编辑超链接",
RemoveLink			: "取消超链接",
VisitLink			: "打开超链接",
Anchor				: "插入/编辑锚点链接",
AnchorDelete		: "清除锚点链接",
InsertImageLbl		: "图象",
InsertImage			: "插入/编辑图象",
InsertFlashLbl		: "Flash",
InsertFlash			: "插入/编辑 Flash",
InsertTableLbl		: "表格",
InsertTable			: "插入/编辑表格",
InsertLineLbl		: "水平�",
InsertLine			: "插入水平�",
InsertSpecialCharLbl: "特殊符号",
InsertSpecialChar	: "插入特殊符号",
InsertSmileyLbl		: "表情符",
InsertSmiley		: "插入表情图标",
About				: "关于 FCKeditor",
Bold				: "加粗",
Italic				: "倾斜",
Underline			: "下划�",
StrikeThrough		: "删除�",
Subscript			: "下标",
Superscript			: "上标",
LeftJustify			: "左对齐",
CenterJustify		: "居中对齐",
RightJustify		: "右对齐",
BlockJustify		: "两端对齐",
DecreaseIndent		: "减少缩�量",
IncreaseIndent		: "增加缩�量",
Blockquote			: "块引用",
CreateDiv			: "插入 Div 标签",
EditDiv				: "编辑 Div 标签",
DeleteDiv			: "删除 Div 标签",
Undo				: "撤消",
Redo				: "重做",
NumberedListLbl		: "编号列表",
NumberedList		: "插入/删除编号列表",
BulletedListLbl		: "项目列表",
BulletedList		: "插入/删除项目列表",
ShowTableBorders	: "显示表格边框",
ShowDetails			: "显示详�资料",
Style				: "样式",
FontFormat			: "格式",
Font				: "字体",
FontSize			: "大小",
TextColor			: "文本颜色",
BGColor				: "背景颜色",
Source				: "源�码",
Find				: "查找",
Replace				: "�换",
SpellCheck			: "拼写检查",
UniversalKeyboard	: "软键盘",
PageBreakLbl		: "分页符",
PageBreak			: "插入分页符",

Form			: "表单",
Checkbox		: "复选框",
RadioButton		: "单选按钮",
TextField		: "单行文本",
Textarea		: "多行文本",
HiddenField		: "隐藏域",
Button			: "按钮",
SelectionField	: "列表/菜单",
ImageButton		: "图像域",

FitWindow		: "全屏编辑",
ShowBlocks		: "显示区块",

// Context Menu
EditLink			: "编辑超链接",
CellCM				: "单元格",
RowCM				: "行",
ColumnCM			: "列",
InsertRowAfter		: "在下方插入行",
InsertRowBefore		: "在上方插入行",
DeleteRows			: "删除行",
InsertColumnAfter	: "在右侧插入列",
InsertColumnBefore	: "在左侧插入列",
DeleteColumns		: "删除列",
InsertCellAfter		: "在右侧插入单元格",
InsertCellBefore	: "在左侧插入单元格",
DeleteCells			: "删除单元格",
MergeCells			: "合并单元格",
MergeRight			: "向右合并单元格",
MergeDown			: "向下合并单元格",
HorizontalSplitCell	: "水平拆分单元格",
VerticalSplitCell	: "垂直拆分单元格",
TableDelete			: "删除表格",
CellProperties		: "单元格属性",
TableProperties		: "表格属性",
ImageProperties		: "图象属性",
FlashProperties		: "Flash 属性",

AnchorProp			: "锚点链接属性",
ButtonProp			: "按钮属性",
CheckboxProp		: "复选框属性",
HiddenFieldProp		: "隐藏域属性",
RadioButtonProp		: "单选按钮属性",
ImageButtonProp		: "图像域属性",
TextFieldProp		: "单行文本属性",
SelectionFieldProp	: "菜单/列表属性",
TextareaProp		: "多行文本属性",
FormProp			: "表单属性",

FontFormats			: "普通;已编排格式;地址;标题 1;标题 2;标题 3;标题 4;标题 5;标题 6;段落(DIV)",

// Alerts and Messages
ProcessingXHTML		: "正在处理 XHTML��请稍等...",
Done				: "完成",
PasteWordConfirm	: "您要粘贴的内容好像是来自 MS Word��是否要清除 MS Word 格式后再粘贴��",
NotCompatiblePaste	: "该命�需要 Internet Explorer 5.5 或更高版本的支持��是否按常规粘贴�行��",
UnknownToolbarItem	: "未知工具栏项目 \"%1\"",
UnknownCommand		: "未知命�名称 \"%1\"",
NotImplemented		: "命�无法执行",
UnknownToolbarSet	: "工具栏设置 \"%1\" 不存在",
NoActiveX			: "浏览器安全设置限制了本编辑器的某些功能。您��启用安全设置中的“�行 ActiveX 控�和插件”��否则将出现某些错误并缺少功能。",
BrowseServerBlocked : "无法打开资源浏览器��请确认是否启用了禁止弹出窗口。",
DialogBlocked		: "无法打开对话框窗口��请确认是否启用了禁止弹出窗口或网页对话框��IE）。",
VisitLinkBlocked	: "无法打开新窗口��请确认是否启用了禁止弹出窗口或网页对话框��IE）。",

// Dialogs
DlgBtnOK			: "确定",
DlgBtnCancel		: "取消",
DlgBtnClose			: "关闭",
DlgBtnBrowseServer	: "浏览服务器",
DlgAdvancedTag		: "高级",
DlgOpOther			: "<其它>",
DlgInfoTab			: "�息",
DlgAlertUrl			: "请插入 URL",

// General Dialogs Labels
DlgGenNotSet		: "<没有设置>",
DlgGenId			: "ID",
DlgGenLangDir		: "语言方向",
DlgGenLangDirLtr	: "�左到右 (LTR)",
DlgGenLangDirRtl	: "�右到左 (RTL)",
DlgGenLangCode		: "语言�码",
DlgGenAccessKey		: "�问键",
DlgGenName			: "名称",
DlgGenTabIndex		: "Tab 键次序",
DlgGenLongDescr		: "详�说明地址",
DlgGenClass			: "样式�名称",
DlgGenTitle			: "标题",
DlgGenContType		: "内容�型",
DlgGenLinkCharset	: "字符编码",
DlgGenStyle			: "行内样式",

// Image Dialog
DlgImgTitle			: "图象属性",
DlgImgInfoTab		: "图象",
DlgImgBtnUpload		: "发送到服务器上",
DlgImgURL			: "源文�",
DlgImgUpload		: "上传",
DlgImgAlt			: "�换文本",
DlgImgWidth			: "宽度",
DlgImgHeight		: "高度",
DlgImgLockRatio		: "锁定比例",
DlgBtnResetSize		: "恢复尺寸",
DlgImgBorder		: "边框大小",
DlgImgHSpace		: "水平间距",
DlgImgVSpace		: "垂直间距",
DlgImgAlign			: "对齐方式",
DlgImgAlignLeft		: "左对齐",
DlgImgAlignAbsBottom: "�对底边",
DlgImgAlignAbsMiddle: "�对居中",
DlgImgAlignBaseline	: "基�",
DlgImgAlignBottom	: "底边",
DlgImgAlignMiddle	: "居中",
DlgImgAlignRight	: "右对齐",
DlgImgAlignTextTop	: "文本上方",
DlgImgAlignTop		: "顶端",
DlgImgPreview		: "预览",
DlgImgAlertUrl		: "请输入图象地址",
DlgImgLinkTab		: "链接",

// Flash Dialog
DlgFlashTitle		: "Flash 属性",
DlgFlashChkPlay		: "自动播放",
DlgFlashChkLoop		: "循环",
DlgFlashChkMenu		: "启用 Flash 菜单",
DlgFlashScale		: "缩放",
DlgFlashScaleAll	: "全部显示",
DlgFlashScaleNoBorder	: "无边框",
DlgFlashScaleFit	: "严格匹配",

// Link Dialog
DlgLnkWindowTitle	: "超链接",
DlgLnkInfoTab		: "超链接�息",
DlgLnkTargetTab		: "目标",

DlgLnkType			: "超链接�型",
DlgLnkTypeURL		: "超链接",
DlgLnkTypeAnchor	: "页内锚点链接",
DlgLnkTypeEMail		: "电子邮�",
DlgLnkProto			: "协议",
DlgLnkProtoOther	: "<其它>",
DlgLnkURL			: "地址",
DlgLnkAnchorSel		: "选择一个锚点",
DlgLnkAnchorByName	: "按锚点名称",
DlgLnkAnchorById	: "按锚点 ID",
DlgLnkNoAnchors		: "(此文档没有可用的锚点)",
DlgLnkEMail			: "地址",
DlgLnkEMailSubject	: "�题",
DlgLnkEMailBody		: "内容",
DlgLnkUpload		: "上传",
DlgLnkBtnUpload		: "发送到服务器上",

DlgLnkTarget		: "目标",
DlgLnkTargetFrame	: "<框架>",
DlgLnkTargetPopup	: "<弹出窗口>",
DlgLnkTargetBlank	: "新窗口 (_blank)",
DlgLnkTargetParent	: "父窗口 (_parent)",
DlgLnkTargetSelf	: "本窗口 (_self)",
DlgLnkTargetTop		: "整页 (_top)",
DlgLnkTargetFrameName	: "目标框架名称",
DlgLnkPopWinName	: "弹出窗口名称",
DlgLnkPopWinFeat	: "弹出窗口属性",
DlgLnkPopResize		: "调整大小",
DlgLnkPopLocation	: "地址栏",
DlgLnkPopMenu		: "菜单栏",
DlgLnkPopScroll		: "�动条",
DlgLnkPopStatus		: "状态栏",
DlgLnkPopToolbar	: "工具栏",
DlgLnkPopFullScrn	: "全屏 (IE)",
DlgLnkPopDependent	: "依附 (NS)",
DlgLnkPopWidth		: "宽",
DlgLnkPopHeight		: "高",
DlgLnkPopLeft		: "左",
DlgLnkPopTop		: "右",

DlnLnkMsgNoUrl		: "请输入超链接地址",
DlnLnkMsgNoEMail	: "请输入电子邮�地址",
DlnLnkMsgNoAnchor	: "请选择一个锚点",
DlnLnkMsgInvPopName	: "弹出窗口名称���字母开头��并且不能含有空格。",

// Color Dialog
DlgColorTitle		: "选择颜色",
DlgColorBtnClear	: "清除",
DlgColorHighlight	: "预览",
DlgColorSelected	: "选择",

// Smiley Dialog
DlgSmileyTitle		: "插入表情图标",

// Special Character Dialog
DlgSpecialCharTitle	: "选择特殊符号",

// Table Dialog
DlgTableTitle		: "表格属性",
DlgTableRows		: "行数",
DlgTableColumns		: "列数",
DlgTableBorder		: "边框",
DlgTableAlign		: "对齐",
DlgTableAlignNotSet	: "<没有设置>",
DlgTableAlignLeft	: "左对齐",
DlgTableAlignCenter	: "居中",
DlgTableAlignRight	: "右对齐",
DlgTableWidth		: "宽度",
DlgTableWidthPx		: "像素",
DlgTableWidthPc		: "百分比",
DlgTableHeight		: "高度",
DlgTableCellSpace	: "间距",
DlgTableCellPad		: "边距",
DlgTableCaption		: "标题",
DlgTableSummary		: "摘要",
DlgTableHeaders		: "标题单元格",
DlgTableHeadersNone		: "无",
DlgTableHeadersColumn	: "第一列",
DlgTableHeadersRow		: "第一行",
DlgTableHeadersBoth		: "第一列和第一行",

// Table Cell Dialog
DlgCellTitle		: "单元格属性",
DlgCellWidth		: "宽度",
DlgCellWidthPx		: "像素",
DlgCellWidthPc		: "百分比",
DlgCellHeight		: "高度",
DlgCellWordWrap		: "自动换行",
DlgCellWordWrapNotSet	: "<没有设置>",
DlgCellWordWrapYes	: "是",
DlgCellWordWrapNo	: "否",
DlgCellHorAlign		: "水平对齐",
DlgCellHorAlignNotSet	: "<没有设置>",
DlgCellHorAlignLeft	: "左对齐",
DlgCellHorAlignCenter	: "居中",
DlgCellHorAlignRight: "右对齐",
DlgCellVerAlign		: "垂直对齐",
DlgCellVerAlignNotSet	: "<没有设置>",
DlgCellVerAlignTop	: "顶端",
DlgCellVerAlignMiddle	: "居中",
DlgCellVerAlignBottom	: "底部",
DlgCellVerAlignBaseline	: "基�",
DlgCellType		: "单元格�型",
DlgCellTypeData		: "资料",
DlgCellTypeHeader	: "标题",
DlgCellRowSpan		: "纵跨行数",
DlgCellCollSpan		: "横跨列数",
DlgCellBackColor	: "背景颜色",
DlgCellBorderColor	: "边框颜色",
DlgCellBtnSelect	: "选择...",

// Find and Replace Dialog
DlgFindAndReplaceTitle	: "查找和�换",

// Find Dialog
DlgFindTitle		: "查找",
DlgFindFindBtn		: "查找",
DlgFindNotFoundMsg	: "指定文本没有找到。",

// Replace Dialog
DlgReplaceTitle			: "�换",
DlgReplaceFindLbl		: "查找:",
DlgReplaceReplaceLbl	: "�换:",
DlgReplaceCaseChk		: "区分大小写",
DlgReplaceReplaceBtn	: "�换",
DlgReplaceReplAllBtn	: "全部�换",
DlgReplaceWordChk		: "全字匹配",

// Paste Operations / Dialog
PasteErrorCut	: "您的浏览器安全设置不允许编辑器自动执行剪切操作��请�用键盘�捷键(Ctrl+X)来完成。",
PasteErrorCopy	: "您的浏览器安全设置不允许编辑器自动执行复制操作��请�用键盘�捷键(Ctrl+C)来完成。",

PasteAsText		: "粘贴为无格式文本",
PasteFromWord	: "� MS Word 粘贴",

DlgPasteMsg2	: "请�用键盘�捷键(<STRONG>Ctrl+V</STRONG>)把内容粘贴到下面的方框里��再按 <STRONG>确定</STRONG>。",
DlgPasteSec		: "因为你的浏览器的安全设置原因��本编辑器不能直接�问你的剪贴�内容��你需要在本窗口重新粘贴一次。",
DlgPasteIgnoreFont		: "�略 Font 标签",
DlgPasteRemoveStyles	: "清理 CSS 样式",

// Color Picker
ColorAutomatic	: "自动",
ColorMoreColors	: "其它颜色...",

// Document Properties
DocProps		: "页面属性",

// Anchor Dialog
DlgAnchorTitle		: "命名锚点",
DlgAnchorName		: "锚点名称",
DlgAnchorErrorName	: "请输入锚点名称",

// Speller Pages Dialog
DlgSpellNotInDic		: "没有在字典里",
DlgSpellChangeTo		: "更改为",
DlgSpellBtnIgnore		: "�略",
DlgSpellBtnIgnoreAll	: "全部�略",
DlgSpellBtnReplace		: "�换",
DlgSpellBtnReplaceAll	: "全部�换",
DlgSpellBtnUndo			: "撤消",
DlgSpellNoSuggestions	: "- 没有�议 -",
DlgSpellProgress		: "正在�行拼写检查...",
DlgSpellNoMispell		: "拼写检查完成��没有发现拼写错误",
DlgSpellNoChanges		: "拼写检查完成��没有更改�何单词",
DlgSpellOneChange		: "拼写检查完成��更改了一个单词",
DlgSpellManyChanges		: "拼写检查完成��更改了 %1 个单词",

IeSpellDownload			: "拼写检查插��没安装��你是否想现在就下载��",

// Button Dialog
DlgButtonText		: "标签(值)",
DlgButtonType		: "�型",
DlgButtonTypeBtn	: "按钮",
DlgButtonTypeSbm	: "提交",
DlgButtonTypeRst	: "重设",

// Checkbox and Radio Button Dialogs
DlgCheckboxName		: "名称",
DlgCheckboxValue	: "选定值",
DlgCheckboxSelected	: "已勾选",

// Form Dialog
DlgFormName		: "名称",
DlgFormAction	: "动作",
DlgFormMethod	: "方法",

// Select Field Dialog
DlgSelectName		: "名称",
DlgSelectValue		: "选定",
DlgSelectSize		: "高度",
DlgSelectLines		: "行",
DlgSelectChkMulti	: "允许多选",
DlgSelectOpAvail	: "列表值",
DlgSelectOpText		: "标签",
DlgSelectOpValue	: "值",
DlgSelectBtnAdd		: "新增",
DlgSelectBtnModify	: "�改",
DlgSelectBtnUp		: "上�",
DlgSelectBtnDown	: "下�",
DlgSelectBtnSetValue : "设为初始化时选定",
DlgSelectBtnDelete	: "删除",

// Textarea Dialog
DlgTextareaName	: "名称",
DlgTextareaCols	: "字符宽度",
DlgTextareaRows	: "行数",

// Text Field Dialog
DlgTextName			: "名称",
DlgTextValue		: "初始值",
DlgTextCharWidth	: "字符宽度",
DlgTextMaxChars		: "最多字符数",
DlgTextType			: "�型",
DlgTextTypeText		: "文本",
DlgTextTypePass		: "密码",

// Hidden Field Dialog
DlgHiddenName	: "名称",
DlgHiddenValue	: "初始值",

// Bulleted List Dialog
BulletedListProp	: "项目列表属性",
NumberedListProp	: "编号列表属性",
DlgLstStart			: "开始序号",
DlgLstType			: "列表�型",
DlgLstTypeCircle	: "圆圈",
DlgLstTypeDisc		: "圆点",
DlgLstTypeSquare	: "方块",
DlgLstTypeNumbers	: "数字 (1, 2, 3)",
DlgLstTypeLCase		: "小写字母 (a, b, c)",
DlgLstTypeUCase		: "大写字母 (A, B, C)",
DlgLstTypeSRoman	: "小写罗马数字 (i, ii, iii)",
DlgLstTypeLRoman	: "大写罗马数字 (I, II, III)",

// Document Properties Dialog
DlgDocGeneralTab	: "常规",
DlgDocBackTab		: "背景",
DlgDocColorsTab		: "颜色和边距",
DlgDocMetaTab		: "Meta 数据",

DlgDocPageTitle		: "页面标题",
DlgDocLangDir		: "语言方向",
DlgDocLangDirLTR	: "�左到右 (LTR)",
DlgDocLangDirRTL	: "�右到左 (RTL)",
DlgDocLangCode		: "语言�码",
DlgDocCharSet		: "字符编码",
DlgDocCharSetCE		: "中欧",
DlgDocCharSetCT		: "繁体中文 (Big5)",
DlgDocCharSetCR		: "�里尔文",
DlgDocCharSetGR		: "希腊文",
DlgDocCharSetJP		: "日文",
DlgDocCharSetKR		: "韩文",
DlgDocCharSetTR		: "土耳其文",
DlgDocCharSetUN		: "Unicode (UTF-8)",
DlgDocCharSetWE		: "�欧",
DlgDocCharSetOther	: "其它字符编码",

DlgDocDocType		: "文档�型",
DlgDocDocTypeOther	: "其它文档�型",
DlgDocIncXHTML		: "包含 XHTML 声明",
DlgDocBgColor		: "背景颜色",
DlgDocBgImage		: "背景图像",
DlgDocBgNoScroll	: "不�动背景图像",
DlgDocCText			: "文本",
DlgDocCLink			: "超链接",
DlgDocCVisited		: "已�问的超链接",
DlgDocCActive		: "�动超链接",
DlgDocMargins		: "页面边距",
DlgDocMaTop			: "上",
DlgDocMaLeft		: "左",
DlgDocMaRight		: "右",
DlgDocMaBottom		: "下",
DlgDocMeIndex		: "页面索引关键字 (用半角逗号[,]分隔)",
DlgDocMeDescr		: "页面说明",
DlgDocMeAuthor		: "作者",
DlgDocMeCopy		: "版权",
DlgDocPreview		: "预览",

// Templates Dialog
Templates			: "模�",
DlgTemplatesTitle	: "内容模�",
DlgTemplatesSelMsg	: "请选择编辑器内容模�:",
DlgTemplatesLoading	: "正在加载模�列表��请稍等...",
DlgTemplatesNoTpl	: "(没有模�)",
DlgTemplatesReplace	: "�换当前内容",

// About Dialog
DlgAboutAboutTab	: "关于",
DlgAboutBrowserInfoTab	: "浏览器�息",
DlgAboutLicenseTab	: "许可证",
DlgAboutVersion		: "版本",
DlgAboutInfo		: "要获得更多�息请�问 ",

// Div Dialog
DlgDivGeneralTab	: "常规",
DlgDivAdvancedTab	: "高级",
DlgDivStyle		: "样式",
DlgDivInlineStyle	: "CSS 样式",

ScaytTitle			: "SCAYT",	//MISSING
ScaytTitleOptions	: "Options",	//MISSING
ScaytTitleLangs		: "Languages",	//MISSING
ScaytTitleAbout		: "About"	//MISSING
};
