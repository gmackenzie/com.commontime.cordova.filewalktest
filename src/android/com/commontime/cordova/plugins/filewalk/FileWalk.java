package com.commontime.cordova.plugins.filewalk;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;

public class FileWalk extends CordovaPlugin {

	public class Filewalker {

		public void walk(File root) {

			File[] list = root.listFiles();

			for (File f : list) {
				if (f.isDirectory()) {
					Log.d("FILEWALKER", "Dir: " + f.getAbsoluteFile());
					walk(f);
				}
				else {
					Log.d("FILEWALKER", "File: " + f.getAbsoluteFile());
				}
			}
		}
	}

    @Override
	protected void pluginInitialize() {

		cordova.getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				Filewalker fw = new Filewalker();
				fw.walk(cordova.getActivity().getFilesDir());
			}
		});
	}

	@Override
	public void onDestroy() {
	}

	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		return false;
	}
}
