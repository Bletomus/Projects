package com.bob.contactsalpha;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;

class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {
    
    private String[] captions;
    private String[] imageIds;
    private Listener listener;

    
    interface Listener
    {
        void onClick(int position);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cardView;
        
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
    
    public CaptionedImagesAdapter(String[] captions, String[] imageIds)
    {
        this.captions = captions;
        this.imageIds = imageIds;
    }
    
    @Override
    public int getItemCount() {
        return captions.length;
    }
    
    public void setListener(Listener listener){
        this.listener = listener;
    }
    
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        Uri uriFromPath = Uri.fromFile(new File(imageIds[position]));
        try
        {
            imageView.setImageBitmap(loadScaledBitmap(uriFromPath, cardView.getContext()));
        } catch (FileNotFoundException e)
        {
            Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), R.drawable.unknown);
            imageView.setImageDrawable(drawable);
        }
     
        
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }
    
    
    private Bitmap loadScaledBitmap(Uri src, Context context) throws FileNotFoundException
    {
        
        final int REQ_WIDTH = 100;
        final int REQ_HEIGHT = 100;
        
        Bitmap bm = null;
        
        
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(src), null, options);
        
        options.inSampleSize = calculateInSampleSize(options, REQ_WIDTH, REQ_HEIGHT);
        
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(src), null, options);
        
        return bm;
    }
    
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        
        if (height > reqHeight || width > reqWidth) {
            
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        
        return inSampleSize;
    }
    
}

