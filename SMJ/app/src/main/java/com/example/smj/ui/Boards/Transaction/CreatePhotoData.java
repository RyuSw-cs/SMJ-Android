package com.example.smj.ui.Boards.Transaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;

public class CreatePhotoData extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView boardImage;
    private static HashMap<String, Bitmap>bitmapHash = new HashMap<String, Bitmap>();

    public CreatePhotoData(String url, ImageView boardImage){
        this.url = url;
        this.boardImage = boardImage;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {
            if(bitmapHash.containsKey(url)){
                Bitmap oldBitmap = bitmapHash.remove(url);
                if(oldBitmap != null && oldBitmap.isRecycled()){
                    oldBitmap.recycle();
                    oldBitmap = null;
                }
            }
            URL dataUrl = new URL(url);
            bitmap = BitmapFactory.decodeStream(dataUrl.openConnection().getInputStream());
            bitmapHash.put(url,bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        boardImage.setImageBitmap(bitmap);
        boardImage.invalidate();
    }
}
