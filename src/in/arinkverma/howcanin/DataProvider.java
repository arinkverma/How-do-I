package in.arinkverma.howcanin;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.apache.cordova.DroidGap;

import android.os.Environment;
import android.webkit.WebView;

public class DataProvider {
	  private WebView mAppView;
	  private DroidGap mGap;
	  
	  public DataProvider(DroidGap gap, WebView view){
	    mAppView = view;
	    mGap = gap;
	  }

	  
	  public String getMenu(){		 
		  return  LoadLocalJson();
	  }
	  
	  private String LoadLocalJson(){		  
		  try {

	            File dir = Environment.getExternalStorageDirectory();
	            File localjson = new File(dir, "/howcani/data.json");
	            FileInputStream stream = new FileInputStream(localjson);
	            String jString = null;
	            try {
	                FileChannel fc = stream.getChannel();
	                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
	                /* Instead of using default, pass in a decoder. */
	                jString = Charset.defaultCharset().decode(bb).toString();
	              }
	              finally {
	                stream.close();
	              }

	            	            
	              return jString;

	        } catch (Exception e) {e.printStackTrace();}
		  
		  	return null;
		  
	  }
	 
	  

	  
}