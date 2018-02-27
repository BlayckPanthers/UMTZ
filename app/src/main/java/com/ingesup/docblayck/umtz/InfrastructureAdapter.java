package com.ingesup.docblayck.umtz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Entities.Infrastructure;

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
            viewHolder.server_name      = (TextView) convertView.findViewById(R.id.infrastructure_server_name);
            viewHolder.server_ip        = (TextView) convertView.findViewById(R.id.infrastructure_server_ip);
            viewHolder.server_status    = (Button) convertView.findViewById(R.id.infrastructure_server_status);
            viewHolder.server_ok        = (Button) convertView.findViewById(R.id.infrastructure_button_ok);
            viewHolder.server_warning   = (Button) convertView.findViewById(R.id.infrastructure_button_warning);
            viewHolder.server_error     = (Button) convertView.findViewById(R.id.infrastructure_button_error);
            viewHolder.server_unkwown   = (Button) convertView.findViewById(R.id.infrastructure_button_unkwown);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Infrastructure> infrastructures
        final Infrastructure Server = getItem(position);

        // Remplissage de la vue.
        viewHolder.server_name.setText(Server.getServer_name());
        viewHolder.server_ip.setText(Server.getServer_ip());
        viewHolder.server_status.setText(String.valueOf(Server.getServer_status()).equals("1")?"up":"Down");
        viewHolder.server_ok.setText(String.valueOf(Server.getServer_ok()));
        viewHolder.server_warning.setText(String.valueOf(Server.getServer_warning()));
        viewHolder.server_unkwown.setText(String.valueOf(Server.getServer_unkwown()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemIntent = new Intent(getContext(), ServerActivity.class);
                itemIntent.putExtra("server_parcelable_extra",Server);
                getContext().startActivity(itemIntent);
            }
        });

        return convertView;
    }

    private class InfrastructureViewHolder {

        public TextView     server_name;
        public TextView     server_ip;
        public Button       server_status;
        public Button       server_ok;
        public Button       server_warning;
        public Button       server_error;
        public Button       server_unkwown;
    }

}
