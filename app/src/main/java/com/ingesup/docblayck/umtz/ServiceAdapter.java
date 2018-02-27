package com.ingesup.docblayck.umtz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.ingesup.docblayck.umtz.Entities.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fabienlebon on 16/02/2018.
 */

public class ServiceAdapter extends ArrayAdapter<Service> {

    public ServiceAdapter(Context context, List<Service> services){
        super(context, 0, services);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_single_layout,parent, false);
        }

        InfrastructureViewHolder viewHolder = (InfrastructureViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new InfrastructureViewHolder();
            viewHolder.service_description        = (TextView) convertView.findViewById(R.id.serviceInfo_Description);
            viewHolder.service_LastCheck    = (TextView) convertView.findViewById(R.id.serviceInfo_LastCheck);
            viewHolder.service_LastStateChange        = (TextView) convertView.findViewById(R.id.serviceInfo_LastStateChange);
            viewHolder.service_State   = (Button) convertView.findViewById(R.id.serviceInfo_State);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Infrastructure> infrastructures
        final Service service = getItem(position);

        // Remplissage de la vue.
        viewHolder.service_description.setText(service.getDescritpion());
        viewHolder.service_State.setBackground(String.valueOf(service.getState()).equals("0")?getContext().getDrawable(R.drawable.ic_greenlight_button):getContext().getDrawable(R.drawable.ic_redlight_button));
        //viewHolder.service_LastCheck.setText(String.valueOf(service.getLast_check()));
        viewHolder.service_LastCheck.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(((Double)service.getLast_check()).intValue())));
        viewHolder.service_LastStateChange.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(((Double)service.getLast_state_change()).intValue())));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent itemIntent = new Intent(getContext(), ServerActivity.class);
                itemIntent.putExtra("server_parcelable_extra",Server);
                getContext().startActivity(itemIntent);*/
            }
        });

        return convertView;
    }

    private class InfrastructureViewHolder {
        public TextView     service_description;
        public TextView     service_LastCheck;
        public TextView     service_LastStateChange;
        public Button       service_State;
    }

}
