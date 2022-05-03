package com.example.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.R;
import com.example.todolistapp.model.Tarefa;

import java.text.SimpleDateFormat;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>{

    private List<Tarefa> tarefas;
    private Context context;

    public TarefaAdapter(List<Tarefa> lista, Context contexto){
        this.tarefas = lista;
        this.context = contexto;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefa, parent, false);

        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {

        Tarefa t = tarefas.get(position);
        holder.tvTitulo.setText(t.getTitulo());

        if (t.isConcluida()){
            holder.tvStatus.setText(R.string.concluida);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.verde_500));
        }else{
            holder.tvStatus.setText(R.string.aberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.azul_claro));
        }

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));
    }

    @Override
    public int getItemCount() {
        if (tarefas != null){
            return tarefas.size();
        }

        return 0;
    }

    class  TarefaViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitulo, tvData, tvStatus;

        public TarefaViewHolder(View view){
            super(view);

            tvTitulo = view.findViewById(R.id.tv_titulo_tarefa);
            tvData = view.findViewById(R.id.tv_data_prevista);
            tvStatus = view.findViewById(R.id.tv_status);
        }
    }

}
