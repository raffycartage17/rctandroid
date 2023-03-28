package com.racartech.library.rctandroid.templates;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TableRow;

import java.util.ArrayList;

public class RCTtemplateTableRow {


    public static int dpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }




    public static int dpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }




    public static int getDrawableSizeDP_From_RowHeightDP(int row_height){
        //Default Ratio =
        double default_ratio = (double)70/(double)120;
        double drawable_size = (double)row_height * default_ratio;
        return (int) drawable_size;
    }

    public static int getDrawableSizeDP_From_RowHeightDP(double ratio,int row_height){
        //Ratio should be below 1.0
        //Ex 0.7 ratio means the drawable is only 70% as big as the row height/button height
        double drawable_size = (double)row_height * ratio;
        return (int) drawable_size;
    }






    public static Button createButton(
            Context context,
            Drawable drawable_top,
            int drawable_size_dp,
            String button_text,
            int button_text_color,
            int button_background_color
            ){



        Button button = new Button(context);
        button.setText(button_text);
        button.setBackgroundColor(button_background_color);
        button.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1));
        if (drawable_top != null) {
            int drawableSize = dpToPx(context, drawable_size_dp);
            drawable_top.setBounds(0, 0, drawableSize, drawableSize);
            int padding = dpToPx(context, 8); // Set your desired padding in dp
            button.setCompoundDrawablePadding(padding);
            button.setCompoundDrawables(null, drawable_top, null, null);
        }
        button.setTextColor(button_text_color);
        return button;
    }

    public static TableRow createRow(Context context,int row_height_dp, ArrayList<Button> row_buttons){
        TableRow tableRow = new TableRow(context);

        //Set the layout weight
        for(int index = 0; index<row_buttons.size(); index++){
            row_buttons.get(index).setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT, 1));
            tableRow.addView(row_buttons.get(index));
        }

        //Set TableRow height and width
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                dpToPx(context, row_height_dp)));
        return tableRow;
    }





}
