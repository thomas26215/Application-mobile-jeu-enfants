package com.example.miniprojet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {

    // Liste des niveaux
    private List<String> levels;
    private OnLevelClickListener listener;

    // Interface pour gérer le clic sur un niveau
    public interface OnLevelClickListener {
        void onLevelClick(String level); // Méthode appelée lorsqu'un niveau est cliqué
    }

    // Constructeur de l'adaptateur
    public LevelAdapter(List<String> levels, OnLevelClickListener listener) {
        this.levels = levels;
        this.listener = listener;
    }

    // Créer et initialiser le ViewHolder pour chaque item de la liste
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infler la vue pour chaque élément
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_item, parent, false);
        return new ViewHolder(view);
    }

    // Associer les données de niveau avec la vue du ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String level = levels.get(position);
        holder.textView.setText(level); // Mettre à jour le texte du niveau

        // Gestion du clic sur un élément de la liste
        holder.itemView.setOnClickListener(v -> listener.onLevelClick(level));
    }

    // Retourne le nombre total d'éléments dans la liste
    @Override
    public int getItemCount() {
        return levels.size(); // Taille de la liste des niveaux
    }

    // Classe ViewHolder pour une vue efficace et réutilisable
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        // Initialisation des vues
        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.levelText); // Récupération du TextView
        }
    }
}

