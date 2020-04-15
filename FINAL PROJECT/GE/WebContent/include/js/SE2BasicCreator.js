function createSEditor2(elIRField, htParams, elSeAppContainer){
	if(!window.$Jindo){
		parent.document.body.innerHTML="���� �����ӿ��� �ʿ��մϴ�.<br>\n<a href='http://dev.naver.com/projects/jindo/download'>http://dev.naver.com/projects/jindo/download</a>���� Jindo 1.5.3 ������ jindo.min.js�� �ٿ�ε� �޾� /js ������ ���� �� �ּ���.\n(���� Jindo 2 �� �������� �ʽ��ϴ�.)";
		return;
	}

	var elAppContainer = (elSeAppContainer || jindo.$("smart_editor2"));	
	var elEditingArea = jindo.$$.getSingle("DIV.husky_seditor_editing_area_container", elAppContainer);
	var oWYSIWYGIFrame = jindo.$$.getSingle("IFRAME.se2_input_wysiwyg", elEditingArea);
	var oIRTextarea = elIRField?elIRField:jindo.$$.getSingle("TEXTAREA.blind", elEditingArea);
	var oHTMLSrc = jindo.$$.getSingle("TEXTAREA.se2_input_htmlsrc", elEditingArea);
	var oTextArea = jindo.$$.getSingle("TEXTAREA.se2_input_text", elEditingArea);
	
	if(!htParams){ 
		htParams = {}; 
		htParams.fOnBeforeUnload = null;
	}
	htParams.elAppContainer = elAppContainer;												// ������ UI �ֻ��� element ���� 
	htParams.oNavigator = jindo.$Agent().navigator();										// navigator ��ü ����
	
	var oEditor = new nhn.husky.HuskyCore(htParams);
	oEditor.registerPlugin(new nhn.husky.CorePlugin(htParams?htParams.fOnAppLoad:null));	
	oEditor.registerPlugin(new nhn.husky.StringConverterManager());

	var htDimension = {
		nMinHeight:205,
		nMinWidth:parseInt(elIRField.style.minWidth, 10)||570,
		nHeight:elIRField.style.height||elIRField.offsetHeight,
		nWidth:elIRField.style.width||elIRField.offsetWidth
	};
	oEditor.registerPlugin(new nhn.husky.SE_EditingAreaManager("WYSIWYG", oIRTextarea, htDimension,  htParams.fOnBeforeUnload, elAppContainer));
	oEditor.registerPlugin(new nhn.husky.SE_EditingArea_WYSIWYG(oWYSIWYGIFrame));			// Tab Editor ���
	oEditor.registerPlugin(new nhn.husky.SE_EditingArea_HTMLSrc(oHTMLSrc));					// Tab HTML ���
	oEditor.registerPlugin(new nhn.husky.SE_EditingArea_TEXT(oTextArea));					// Tab Text ���
	oEditor.registerPlugin(new nhn.husky.SE2M_EditingModeChanger(elAppContainer));			// ��尣 ����(Editor, HTML, Text)
	
	oEditor.registerPlugin(new nhn.husky.HuskyRangeManager(oWYSIWYGIFrame));
	oEditor.registerPlugin(new nhn.husky.Utils());
	oEditor.registerPlugin(new nhn.husky.SE2M_UtilPlugin());
	oEditor.registerPlugin(new nhn.husky.SE_WYSIWYGStyler());
	oEditor.registerPlugin(new nhn.husky.SE2M_Toolbar(elAppContainer));
	
	oEditor.registerPlugin(new nhn.husky.Hotkey());											// ����Ű
	oEditor.registerPlugin(new nhn.husky.SE_EditingAreaVerticalResizer(elAppContainer));	// �������� ��������
	oEditor.registerPlugin(new nhn.husky.DialogLayerManager());
	oEditor.registerPlugin(new nhn.husky.ActiveLayerManager());
	oEditor.registerPlugin(new nhn.husky.SE_WYSIWYGStyleGetter());							// Ŀ�� ��ġ ��Ÿ�� ���� ��������

	oEditor.registerPlugin(new nhn.husky.SE2B_Customize_ToolBar(elAppContainer));			// ��� ���� (Basic)
	oEditor.registerPlugin(new nhn.husky.SE_WYSIWYGEnterKey("P"));							// ���� �� ó��, ����� P�� ó��
	
	oEditor.registerPlugin(new nhn.husky.SE2M_ColorPalette(elAppContainer));				// ���� �ȷ�Ʈ
	oEditor.registerPlugin(new nhn.husky.SE2M_FontColor(elAppContainer));					// ���ڻ�
	oEditor.registerPlugin(new nhn.husky.SE2M_BGColor(elAppContainer));						// ���ڹ���
	oEditor.registerPlugin(new nhn.husky.SE2M_FontNameWithLayerUI(elAppContainer));			// �۲�����
	oEditor.registerPlugin(new nhn.husky.SE2M_FontSizeWithLayerUI(elAppContainer));			// �۲�ũ��
	
	oEditor.registerPlugin(new nhn.husky.SE2M_LineStyler());								 
	oEditor.registerPlugin(new nhn.husky.SE2M_ExecCommand(oWYSIWYGIFrame));
	oEditor.registerPlugin(new nhn.husky.SE2M_LineHeightWithLayerUI(elAppContainer));		// �ٰ���	

	oEditor.registerPlugin(new nhn.husky.SE2M_Quote(elAppContainer));						// �ο뱸
	oEditor.registerPlugin(new nhn.husky.SE2M_Hyperlink(elAppContainer));					// ��ũ
	oEditor.registerPlugin(new nhn.husky.SE2M_SCharacter(elAppContainer));					// Ư������
	oEditor.registerPlugin(new nhn.husky.SE2M_FindReplacePlugin(elAppContainer));			// ã��/�ٲٱ�
	oEditor.registerPlugin(new nhn.husky.SE2M_TableCreator(elAppContainer));				// ���̺� ����
	oEditor.registerPlugin(new nhn.husky.SE2M_TableEditor(elAppContainer));					// ���̺� ����
	oEditor.registerPlugin(new nhn.husky.SE2M_TableBlockStyler(elAppContainer));			// ���̺� ��Ÿ��
	oEditor.registerPlugin(new nhn.husky.SE2M_AttachQuickPhoto(elAppContainer));			// ����			

	oEditor.registerPlugin(new nhn.husky.MessageManager(oMessageMap));
	oEditor.registerPlugin(new nhn.husky.SE2M_QuickEditor_Common(elAppContainer));			// �������� ����(ǥ, �̹���)
	
	if(jindo.$Agent().navigator().ie){
		oEditor.registerPlugin(new nhn.husky.SE2M_ImgSizeRatioKeeper());					// �̹��� ������ ���� ���콺�� ũ�� �����ϸ� �������� ����		
	}
		
	oEditor.registerPlugin(new nhn.husky.SE2B_CSSLoader());									// CSS lazy load
	oEditor.registerPlugin(new nhn.husky.SE_OuterIFrameControl(elAppContainer, 100));
	
	oEditor.registerPlugin(new nhn.husky.SE_ToolbarToggler(elAppContainer, htParams.bUseToolbar));
	
	return oEditor;
}