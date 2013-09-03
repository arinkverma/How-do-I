 
package in.arinkverma.howcanin;

import org.apache.cordova.DroidGap;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TaskActivity extends DroidGap
{
	private DataProvider mc;
	

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init(); 
        mc = new DataProvider(this, appView);
        appView.addJavascriptInterface(mc, "MyCls");      
        super.loadUrl("file:///android_asset/www/index.html");
    }
    
    
   public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.option_menu, menu);
       return true;
    }

   public boolean onOptionsItemSelected(MenuItem item){	 
   	Intent intent = null;
       switch (item.getItemId()) {
	        case R.id.refresh:
	            intent = new Intent(TaskActivity.this, SetupActivity.class);
	            intent.putExtra("force", true);
	            startActivity(intent);
	            return true;
	        
           default:
               return super.onOptionsItemSelected(item);
       }
   }
    
    
}

