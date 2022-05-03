package com.example.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistapp.R;
import com.example.todolistapp.adapter.TarefaAdapter;
import com.example.todolistapp.database.AppDatabase;
import com.example.todolistapp.databinding.FragmentPrincipalBinding;
import com.example.todolistapp.model.Tarefa;

import java.util.List;


public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;
    private AppDatabase database;
    private TarefaAdapter adapter;
    private List<Tarefa> tarefas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPrincipalBinding.inflate(inflater, container, false);

        binding.btnAddTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadastroTarefaFragment);
        });

        return binding.getRoot();
    }
}