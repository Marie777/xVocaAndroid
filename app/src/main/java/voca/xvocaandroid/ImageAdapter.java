package voca.xvocaandroid;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{

    private Context mContext;
    private Integer[] mThumbIDs;

    public ImageAdapter(Context c, Integer[] thumbs){
        mContext = c;
        mThumbIDs = thumbs;
    }

    @Override
    public int getCount() {
        return mThumbIDs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }else{
            imageView = (ImageView)convertView;
        }
        imageView.setImageResource(mThumbIDs[position]);
        return imageView;
    }
}
