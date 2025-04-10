package com.example.miniprojet.Entités.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuestionGenerator {

    private List<Question> questions;
    private Random random = new Random();

    public QuestionGenerator() {
        this.questions = initializeQuestions();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getQuestionById(int id) {
        if (id >= 1 && id <= questions.size()) {
            return questions.get(id - 1); // Ajuster l'index car les ID commencent à 1
        } else {
            return null; // Ou lancer une exception, selon votre besoin
        }
    }

    public int getNombreQuestions() {
        return questions.size();
    }

    public Question getEasyQuestion(int id) {
        List<Question> easyQuestions = questions.stream()
                .filter(q -> q.difficulty.equals("FACILE"))
                .collect(Collectors.toList());
        return easyQuestions.get(id);
    }

    public Question getMediumQuestion(int id) {
        List<Question> mediumQuestions = questions.stream()
                .filter(q -> q.difficulty.equals("MOYEN"))
                .collect(Collectors.toList());
        return mediumQuestions.get(id);
    }

    public Question getHardQuestion(int id) {
        List<Question> hardQuestions = questions.stream()
                .filter(q -> q.difficulty.equals("DIFFICILE"))
                .collect(Collectors.toList());
        return hardQuestions.get(id);
    }

    public int getEasyQuestionsCount() {
        return (int) questions.stream()
                .filter(q -> q.difficulty.equals("FACILE"))
                .count();
    }

    public int getMediumQuestionsCount() {
        return (int) questions.stream()
                .filter(q -> q.difficulty.equals("MOYEN"))
                .count();
    }

    public int getHardQuestionsCount() {
        return (int) questions.stream()
                .filter(q -> q.difficulty.equals("DIFFICILE"))
                .count();
    }

    private List<Question> initializeQuestions() {
        List<Question> questionList = new ArrayList<>();

        // Questions faciles
        questionList.add(new Question("Quel est le son d'un chat ?", "Miaou", "Les chats font 'miaou'.", "FACILE", "ANIMAUX"));
        questionList.add(new Question("Quelle est la couleur d'une banane mûre ?", "Jaune", "Les bananes mûres sont jaunes.", "FACILE", "FRUITS"));
        questionList.add(new Question("Combien de pattes a un chien ?", "Quatre", "Les chiens ont quatre pattes.", "FACILE", "ANIMAUX"));
        questionList.add(new Question("Quelle planète est connue comme la planète rouge ?", "Mars", "Mars est appelée la planète rouge.", "FACILE", "ESPACE"));
        questionList.add(new Question("Quel est le plus grand océan du monde ?", "Pacifique", "L'océan Pacifique est le plus grand.", "FACILE", "GÉOGRAPHIE"));
        questionList.add(new Question("Combien de côtés a un triangle ?", "Trois", "Un triangle a toujours trois côtés.", "FACILE", "MATHÉMATIQUES"));
        questionList.add(new Question("Quel est l'opposé de 'chaud' ?", "Froid", "'Froid' est l'opposé de 'chaud'.", "FACILE", "VOCABULAIRE"));
        questionList.add(new Question("Quelle est la capitale de la France ?", "Paris", "Paris est la capitale de la France.", "FACILE", "GÉOGRAPHIE"));
        questionList.add(new Question("Quel est le plus grand mammifère terrestre ?", "Éléphant", "L'éléphant est le plus grand mammifère terrestre.", "FACILE", "ANIMAUX"));
        questionList.add(new Question("Combien de doigts a une main humaine ?", "Cinq", "Une main humaine a cinq doigts.", "FACILE", "CORPS HUMAIN"));
        questionList.add(new Question("Quel est le contraire de 'jour' ?", "Nuit", "'Nuit' est le contraire de 'jour'.", "FACILE", "VOCABULAIRE"));
        questionList.add(new Question("Quelle planète est connue pour ses anneaux ?", "Saturne", "Saturne est connue pour ses anneaux visibles.", "FACILE", "ESPACE"));
        questionList.add(new Question("Quel est le plus petit mois de l'année ?", "Février", "Février est le mois le plus court.", "FACILE", "TEMPS"));
        questionList.add(new Question("Quelle est la couleur du ciel par temps clair ?", "Bleu", "Le ciel est généralement bleu par temps clair.", "FACILE", "NATURE"));
        questionList.add(new Question("Quel est l'animal symbole de l'Australie ?", "Kangourou", "Le kangourou est un symbole de l'Australie.", "FACILE", "GÉOGRAPHIE"));
        questionList.add(new Question("Combien de jours y a-t-il dans une semaine ?", "Sept", "Il y a sept jours dans une semaine.", "FACILE", "TEMPS"));
        questionList.add(new Question("Quel est le plus grand félin ?", "Tigre", "Le tigre est le plus grand félin.", "FACILE", "ANIMAUX"));
        questionList.add(new Question("Quelle est la forme de la Terre ?", "Sphère", "La Terre est une sphère (presque parfaite).", "FACILE", "GÉOGRAPHIE"));
        questionList.add(new Question("Quel est l'organe principal de la respiration ?", "Poumons", "Les poumons sont les organes principaux de la respiration.", "FACILE", "CORPS HUMAIN"));
        questionList.add(new Question("Quel est le contraire de 'rapide' ?", "Lent", "'Lent' est le contraire de 'rapide'.", "FACILE", "VOCABULAIRE"));
        // Questions moyennes
        questionList.add(new Question("Qu'est-ce que la photosynthèse ?", "Nourriture", "La photosynthèse est le processus par lequel les plantes produisent leur nourriture.", "MOYEN", "BIOLOGIE"));
        questionList.add(new Question("Quel est le plus grand désert du monde ?", "Sahara", "Le Sahara est le plus grand désert chaud du monde.", "MOYEN", "GÉOGRAPHIE"));
        questionList.add(new Question("Qu'est-ce qu'un mammifère ?", "Allaitement", "Un mammifère est un animal qui allaite ses petits.", "MOYEN", "BIOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la gravité ?", "Attraction", "La gravité est une force d'attraction entre les objets.", "MOYEN", "PHYSIQUE"));
        questionList.add(new Question("Quel est le plus long fleuve du monde ?", "Nil", "Le Nil est considéré comme le plus long fleuve du monde.", "MOYEN", "GÉOGRAPHIE"));
        questionList.add(new Question("Qu'est-ce qu'un nombre premier ?", "Indivisible", "Un nombre premier n'est divisible que par 1 et par lui-même.", "MOYEN", "MATHÉMATIQUES"));
        questionList.add(new Question("Qu'est-ce que l'hibernation ?", "Sommeil", "L'hibernation est un état de sommeil prolongé chez certains animaux.", "MOYEN", "BIOLOGIE"));
        questionList.add(new Question("Qu'est-ce qu'un volcan ?", "Éruption", "Un volcan est une ouverture dans la croûte terrestre qui peut provoquer des éruptions.", "MOYEN", "GÉOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la démocratie ?", "Vote", "La démocratie est un système où le peuple vote pour choisir ses représentants.", "MOYEN", "POLITIQUE"));
        questionList.add(new Question("Qu'est-ce que la photographie ?", "Image", "La photographie est l'art de capturer des images.", "MOYEN", "ART"));
        questionList.add(new Question("Qu'est-ce qu'un écosystème ?", "Interaction", "Un écosystème est un ensemble d'êtres vivants en interaction avec leur environnement.", "MOYEN", "ÉCOLOGIE"));
        questionList.add(new Question("Qu'est-ce que le système solaire ?", "Planètes", "Le système solaire est l'ensemble des planètes orbitant autour du Soleil.", "MOYEN", "ASTRONOMIE"));
        questionList.add(new Question("Qu'est-ce que la digestion ?", "Transformation", "La digestion est le processus de transformation des aliments en nutriments.", "MOYEN", "BIOLOGIE"));
        questionList.add(new Question("Qu'est-ce qu'un adjectif ?", "Qualificatif", "Un adjectif est un mot qui qualifie ou décrit un nom.", "MOYEN", "GRAMMAIRE"));
        questionList.add(new Question("Qu'est-ce que le réchauffement climatique ?", "Température", "Le réchauffement climatique est l'augmentation de la température moyenne de la Terre.", "MOYEN", "ENVIRONNEMENT"));
        questionList.add(new Question("Qu'est-ce qu'un fossile ?", "Empreinte", "Un fossile est une empreinte ou un reste d'un organisme ancien.", "MOYEN", "PALÉONTOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la symétrie ?", "Équilibre", "La symétrie est un équilibre ou une correspondance exacte des parties.", "MOYEN", "MATHÉMATIQUES"));
        questionList.add(new Question("Qu'est-ce que l'énergie renouvelable ?", "Durable", "L'énergie renouvelable est une forme d'énergie durable et inépuisable.", "MOYEN", "ENVIRONNEMENT"));
        questionList.add(new Question("Qu'est-ce que la tectonique des plaques ?", "Mouvement", "La tectonique des plaques explique le mouvement des plaques de la croûte terrestre.", "MOYEN", "GÉOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la biodiversité ?", "Variété", "La biodiversité représente la variété des espèces vivantes sur Terre.", "MOYEN", "ÉCOLOGIE"));

        // Questions difficiles
        questionList.add(new Question("Qu'est-ce que l'ADN ?", "Gènes", "L'ADN est une molécule portant les instructions génétiques des êtres vivants.", "DIFFICILE", "BIOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la théorie de la relativité ?", "Espace-temps", "La théorie de la relativité d'Einstein explique le fonctionnement de l'espace-temps.", "DIFFICILE", "PHYSIQUE"));
        questionList.add(new Question("Qu'est-ce que la photosynthèse ?", "Lumière", "La photosynthèse est le processus par lequel les plantes convertissent la lumière en énergie.", "DIFFICILE", "BIOLOGIE"));
        questionList.add(new Question("Qu'est-ce que le Big Bang ?", "Expansion", "Le Big Bang est une théorie expliquant l'origine et l'expansion de l'univers.", "DIFFICILE", "ASTRONOMIE"));
        questionList.add(new Question("Qu'est-ce que la tectonique des plaques ?", "Continents", "La tectonique des plaques explique le mouvement des continents.", "DIFFICILE", "GÉOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la théorie de l'évolution ?", "Adaptation", "La théorie de l'évolution explique comment les espèces s'adaptent au fil du temps.", "DIFFICILE", "BIOLOGIE"));
        questionList.add(new Question("Qu'est-ce que le changement climatique ?", "Réchauffement", "Le changement climatique est la modification à long terme des conditions météorologiques.", "DIFFICILE", "ENVIRONNEMENT"));
        questionList.add(new Question("Qu'est-ce que la division cellulaire ?", "Reproduction", "La division cellulaire est le processus par lequel une cellule se reproduit.", "DIFFICILE", "BIOLOGIE"));
        questionList.add(new Question("Qu'est-ce que l'effet de serre ?", "Chaleur", "L'effet de serre est le processus de rétention de la chaleur dans l'atmosphère terrestre.", "DIFFICILE", "ENVIRONNEMENT"));
        questionList.add(new Question("Qu'est-ce que la théorie des cordes ?", "Vibration", "La théorie des cordes propose que les particules sont des vibrations de cordes.", "DIFFICILE", "PHYSIQUE"));
        questionList.add(new Question("Qu'est-ce que la biodiversité ?", "Écosystèmes", "La biodiversité représente la variété des écosystèmes et des espèces vivantes.", "DIFFICILE", "ÉCOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la théorie quantique ?", "Probabilité", "La théorie quantique explique le comportement des particules en termes de probabilités.", "DIFFICILE", "PHYSIQUE"));
        questionList.add(new Question("Qu'est-ce que la plate tectonique ?", "Dérive", "La plate tectonique explique la dérive des continents.", "DIFFICILE", "GÉOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la théorie du chaos ?", "Imprévisibilité", "La théorie du chaos étudie l'imprévisibilité des systèmes complexes.", "DIFFICILE", "MATHÉMATIQUES"));
        questionList.add(new Question("Qu'est-ce que la fusion nucléaire ?", "Énergie", "La fusion nucléaire est un processus qui libère une énorme quantité d'énergie.", "DIFFICILE", "PHYSIQUE"));
        questionList.add(new Question("Qu'est-ce que la théorie des jeux ?", "Stratégie", "La théorie des jeux étudie les stratégies dans des situations de conflit ou de coopération.", "DIFFICILE", "MATHÉMATIQUES"));
        questionList.add(new Question("Qu'est-ce que la nanotechnologie ?", "Miniaturisation", "La nanotechnologie implique la manipulation de la matière à l'échelle nanométrique.", "DIFFICILE", "TECHNOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la biotechnologie ?", "Organismes", "La biotechnologie utilise des organismes vivants pour développer des produits.", "DIFFICILE", "TECHNOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la géologie structurale ?", "Déformation", "La géologie structurale étudie la déformation des roches et de la croûte terrestre.", "DIFFICILE", "GÉOLOGIE"));
        questionList.add(new Question("Qu'est-ce que la météorologie ?", "Prévision", "La météorologie est l'étude et la prévision des phénomènes atmosphériques.", "DIFFICILE", "MÉTÉOROLOGIE"));
        return questionList;
    }

    public static class Question {
        public String questionText;
        public String expectedAnswer;
        public String explanation;
        public String difficulty;
        public String category;

        public Question(String questionText, String expectedAnswer, String explanation, String difficulty, String category) {
            this.questionText = questionText;
            this.expectedAnswer = expectedAnswer;
            this.explanation = explanation;
            this.difficulty = difficulty;
            this.category = category;
        }
    }
}
