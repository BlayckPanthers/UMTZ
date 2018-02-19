package com.ingesup.docblayck.umtz;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by fabienlebon on 16/02/2018.
 */

public class InfrastructureAdapter extends ArrayAdapter<Infrastructure> {

    public InfrastructureAdapter(Context context, List<Infrastructure> infrastructures){
        super(context, 0, infrastructures);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.infrastructure_single_layout,parent, false);
        }

        InfrastructureViewHolder viewHolder = (InfrastructureViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new InfrastructureViewHolder();
            viewHolder.server_name = (TextView) convertView.findViewById(R.id.infrastructure_server_name);
            viewHolder.server_ip = (TextView) convertView.findViewById(R.id.infrastructure_server_ip);
            viewHolder.server_status = (ImageView) convertView.findViewById(R.id.infrastructure_server_status);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Infrastructure> infrastructures
        Infrastructure Infrastructure = getItem(position);

        // Remplissage de la vue.
        viewHolder.server_name.setText(Infrastructure.getServer_name());
        viewHolder.server_ip.setText(Infrastructure.getServer_ip());
        viewHolder.server_status.setImageResource(Infrastructure.getServer_status());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getContext(), getItem(position).getServer_ip()
                        + " " + getItem(position).getServer_name()
                        , Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    private class InfrastructureViewHolder {

        public TextView     server_name;
        public TextView     server_ip;
        public ImageView    server_status;
    }

}