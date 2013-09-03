package in.arinkverma.howcanin;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;



public class HowPlugin  extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    	Log.e("call",action);
    	
    	
        if (action.equals("openTask")) {
            String message = args.getString(0); 
            echo(message,callbackContext);
            Log.e("call",args.toString());
            return true;
        }
        return false;
    }
    


    private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) { 
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}