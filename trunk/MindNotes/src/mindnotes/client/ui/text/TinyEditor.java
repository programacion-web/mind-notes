package mindnotes.client.ui.text;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class TinyEditor {
	private JavaScriptObject _nativeEditor;
	private TinyConfig _config = new TinyConfig();
	private Element _div = null;

	public TinyEditor() {
	}

	public void attach(Element div) {
		if (_div != null) {
			detach();
		}
		setDiv(div);
		attachEditor(_div.getId(), _config.getNativeConfig());
	}

	private void setDiv(Element div) {
		_div = div;
		String id = _div.getId();
		if (id == null || id.equals(""))
			_div.setId(DOM.createUniqueId());

	}

	public void detach() {
		if (_div == null) {
			return;
		}
		detatchEditor(_div.getId());
		_div = null;
	}

	private native void detatchEditor(String id) /*-{
		var e = this.@mindnotes.client.ui.text.TinyEditor::_nativeEditor;
		e.save();
		$wnd.tinymce.execCommand("mceRemoveControl", false, id);
	}-*/;

	private native void attachEditor(String id, JavaScriptObject config) /*-{
		$wnd.tinymce.init(config);
		var editor = new $wnd.tinymce.Editor(id, config);
		this.@mindnotes.client.ui.text.TinyEditor::_nativeEditor = editor;

		editor.render();
	}-*/;

	public void setFocus(boolean focused) {
		if (focused && _div != null) {
			focusTiny(_div.getId());
		}
	}

	private native void focusTiny(String id) /*-{
		$wnd.tinymce.execCommand('mceFocus', false, id);
	}-*/;

}