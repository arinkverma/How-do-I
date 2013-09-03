package in.arinkverma.howcanin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class SetupActivity extends Activity {
	// declare the dialog as a member field of your activity
	  ProgressDialog mProgressDialog;
	  Intent intent;
	  boolean isForce;
	/** Called when the activity is first created. */
	  @Override
	public void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  	Intent rintent = getIntent();
	  	isForce = rintent.getBooleanExtra("force", false);
	  
	    intent = new Intent(this, TaskActivity.class);
	    
	    String loc = Environment.getExternalStorageDirectory().toString()+"/howcani/data.json";
        
	    File file = new File(loc);
        if(!file.exists() || isForce ){	
			  // instantiate it within the onCreate method
			  mProgressDialog = new ProgressDialog(SetupActivity.this);
			  mProgressDialog.setMessage("Downloading Data..");
			  mProgressDialog.setIndeterminate(false);
			  mProgressDialog.setMax(100);
			  mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			  mProgressDialog.setCancelable(false);		
			  // execute this when the downloader must be fired
			  DownloadFile downloadFile = new DownloadFile();
			  downloadFile.execute("https://docs.google.com/spreadsheet/pub?key=0AnPqyp8kuQw0dExRYVg3b0YxajQ5QWhSVFg0ZXUwVFE&output=txt");
        }else{
        	intent.setFlags(intent.getFlags() |Intent.FLAG_ACTIVITY_NO_HISTORY);
			  startActivity(intent);
        }
	}
	  
	 private class DownloadFile extends AsyncTask<String, Integer, Long> {
		  @Override
		  protected Long doInBackground(String... sUrl) {
		      try {
		          URL url = new URL(sUrl[0]);
		          URLConnection urlConnection = url.openConnection();
		          urlConnection.connect();
		          
	
		          int  fileLength = urlConnection.getContentLength();
		          // this will be useful so that you can show a typical 0-100% progress bar
		          
		          // download the file
		          InputStream input = new BufferedInputStream(url.openStream());
		          
		          String loc = Environment.getExternalStorageDirectory().toString()+"/howcani";
		          
		          File file = new File(loc);
		          if(!file.exists()){
		        	  file.mkdirs();	
		  	    	}
		          OutputStream output = new FileOutputStream(loc+"/data.json");
		          Log.i("FileSize",fileLength+"");
		          
		          byte data[] = new byte[1024];
		          long total = 0;
		          int count;
		          while ((count = input.read(data)) != -1) {
		              total += count;
		              // publishing the progress....
		              publishProgress((int) (total * 100 / fileLength));
		              output.write(data, 0, count);
		          }

		          output.flush();
		          output.close();
		          input.close();
		      } catch (Exception e) {
		      }
		      return null;
		  }
		  
		  
		  protected void onPreExecute() {
		      super.onPreExecute();
		      mProgressDialog.show();
		  }

		  @Override
		  protected void onProgressUpdate(Integer... progress) {
		      super.onProgressUpdate(progress);
		      Log.i("progress",progress[0]+"");
		      mProgressDialog.setProgress(progress[0]);
		  }
		  
		  @Override
		  protected void onPostExecute(Long result) {
			  super.onPostExecute(result);
			  mProgressDialog.dismiss();
			  startActivity(intent);
		  }

	  }
	  
}
