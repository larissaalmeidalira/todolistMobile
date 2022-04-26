package com.example.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.todolistapp.R;
import com.example.todolistapp.database.AppDatabase;
import com.example.todolistapp.databinding.FragmentCadastroTarefaBinding;
import com.example.todolistapp.model.Tarefa;

import java.util.Calendar;


public class CadastroTarefaFragment extends Fragment {

  private FragmentCadastroTarefaBinding binding;

  DatePickerDialog datePicker;
  int year, month, day;
  Calendar dataAtual;
  String dataEscolhida = "";
  AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database = AppDatabase.getDatabase(getActivity());

        binding = FragmentCadastroTarefaBinding.inflate(inflater, container, false);

        dataAtual = Calendar.getInstance();
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia) -> {
            year = ano;
            month = mes;
            day = dia;

            dataEscolhida = String.format("%02d/%02d/%04d", day, month+1, year);
            binding.buttonData.setText(dataEscolhida);

        }, year, month, day);

        binding.buttonData.setOnClickListener(v -> {
            datePicker.show();
        });

        binding.buttonSalvar.setOnClickListener(v -> {
            if(binding.editTitulo.getText().toString().isEmpty()) {
                binding.editTitulo.setError(getString(R.string.obrigatorio));
            }else if(dataEscolhida.isEmpty()){
                binding.buttonData.setError(getString(R.string.obrigatorio));
                Toast.makeText(getContext(), "Escolha uma data", Toast.LENGTH_SHORT).show();
            }else{
                Tarefa tarefa = new Tarefa();
                tarefa.setTitulo(binding.editTitulo.getText().toString());
                tarefa.setTitulo(binding.editDescricao.getText().toString());
                Calendar dataRealizacao = Calendar.getInstance();
                dataRealizacao.set(year, month, day);
                tarefa.setDataPrevista(dataRealizacao.getTimeInMillis());
                Calendar dataAtual = Calendar.getInstance();
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                new InsertTarefa().execute(tarefa);
            }
        });

        return binding.getRoot();
    }

    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.w("Passou!!", "no onPreExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.w("Passou!!", "no onProgressUpdate");
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            Log.w("Passou!!", "no doInBackground");
            Tarefa t = tarefas[0];
            try {
                database.getTarefaDao().insert(t);
                return "ok";
            }catch (Exception e){
                e.printStackTrace();
                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String resultado) {
            if (resultado.equals("ok")){
                Log.w("Passou!!", "!!!");
            }else{
                Log.w("RESULTADO", resultado);
                Toast.makeText(getContext(), "DEU RUIM"+resultado, Toast.LENGTH_SHORT).show();
            }
        }
    }

}