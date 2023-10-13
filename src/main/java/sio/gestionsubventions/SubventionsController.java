package sio.gestionsubventions;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.gestionsubventions.Model.Structure;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SubventionsController implements Initializable {
    HashMap<String, HashMap<String, TreeMap<Integer, ArrayList<Structure>>>>lesSubventions;
    TreeItem<String> rootSecteur;
    TreeItem<String> rootAnnee;
    TreeItem<String> rootVille;

    @FXML
    private AnchorPane apAffecter;
    @FXML
    private ListView<String> lvVilles;
    @FXML
    private AnchorPane apStatistiques;
    @FXML
    private ListView<String> lvSecteurs;
    @FXML
    private ComboBox<Integer> cboAnnees;
    @FXML
    private TextField txtNomStructure;
    @FXML
    private TextField txtMontant;
    @FXML
    private Button btnAffecterSubvention;
    @FXML
    private Button btnMenuAffecter;
    @FXML
    private Button btnMenuStatistiques;
    @FXML
    private ListView<String> lvVillesStats;
    @FXML
    private TreeView<String> tvMontantsParSecteurs;
    @FXML
    private TreeView<String> tvMontantsParAnnees;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        apAffecter.toFront();
        lesSubventions = new HashMap<>();
        lvVilles.getItems().addAll("Bordeaux", "Nantes", "Paris");
        lvSecteurs.getItems().addAll("Culture", "Education", "Santé", "Sport");

        cboAnnees.getItems().addAll(2020, 2021, 2022, 2023, 2024, 2025);
        cboAnnees.getSelectionModel().selectFirst();

        rootSecteur = new TreeItem<>("Par secteurs");
        tvMontantsParSecteurs.setRoot(rootSecteur);

        rootAnnee = new TreeItem<>("Par année");
        tvMontantsParAnnees.setRoot(rootAnnee);

        // Jeu d'essais au cas où :)
//        Structure structure1 = new Structure("Structure 1",1000);
//        Structure structure2 = new Structure("Structure 2",2000);
//        Structure structure3 = new Structure("Structure 3",3000);
//        Structure structure4 = new Structure("Structure 4",4000);
//        Structure structure5 = new Structure("Structure 5",5000);
//        Structure structure6 = new Structure("Structure 6",6000);
//        Structure structure7 = new Structure("Structure 7",7000);
//        Structure structure8 = new Structure("Structure 8",8000);
//        Structure structure9 = new Structure("Structure 9",9000);
//
//        ArrayList<Structure> lesStructuresDeBordeaux = new ArrayList<>();
//        lesStructuresDeBordeaux.add(structure1);
//        lesStructuresDeBordeaux.add(structure2);
//        lesStructuresDeBordeaux.add(structure3);
//
//        ArrayList<Structure> lesStructuresDeNantes = new ArrayList<>();
//        lesStructuresDeNantes.add(structure4);
//        lesStructuresDeNantes.add(structure5);
//        lesStructuresDeNantes.add(structure6);
//
//        ArrayList<Structure> lesStructuresDeParis = new ArrayList<>();
//        lesStructuresDeParis.add(structure7);
//        lesStructuresDeParis.add(structure8);
//        lesStructuresDeParis.add(structure9);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeBordeaux = new TreeMap<>();
//        lesAnneesDeBordeaux.put(2020, lesStructuresDeBordeaux);
//        lesAnneesDeBordeaux.put(2021, lesStructuresDeBordeaux);
//        lesAnneesDeBordeaux.put(2022, lesStructuresDeBordeaux);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeNantes = new TreeMap<>();
//        lesAnneesDeNantes.put(2020, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2021, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2022, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2023, lesStructuresDeNantes);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeParis = new TreeMap<>();
//        lesAnneesDeParis.put(2022, lesStructuresDeParis);
//        lesAnneesDeParis.put(2023, lesStructuresDeParis);
//        lesAnneesDeParis.put(2024, lesStructuresDeParis);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeBordeaux = new HashMap<>();
//        lesSecteursDeBordeaux.put("Santé", lesAnneesDeBordeaux);
//        lesSecteursDeBordeaux.put("Sport", lesAnneesDeBordeaux);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeNantes = new HashMap<>();
//        lesSecteursDeNantes.put("Education", lesAnneesDeNantes);
//        lesSecteursDeNantes.put("Culture", lesAnneesDeNantes);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeParis = new HashMap<>();
//        lesSecteursDeParis.put("Culture", lesAnneesDeParis);
//
//        lesSubventions.put("Bordeaux",lesSecteursDeBordeaux);
//        lesSubventions.put("Nantes",lesSecteursDeNantes);
//        lesSubventions.put("Paris",lesSecteursDeParis);

    }

    @FXML
    public void btnMenuClicked(Event event)
    {
        if (event.getSource() == btnMenuAffecter)
        {
            apAffecter.toFront();
        } else if (event.getSource() == btnMenuStatistiques)
        {
            apStatistiques.toFront();
        }
    }

    @FXML
    public void btnAffecterSubventionClicked(Event event)
    {
        if (lvVilles.getSelectionModel().getSelectedItem() == null)
        {
            // Gestion de l'alerte pour la ville non sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Choix de la ville");
            alert.setContentText("Sélectionner une ville");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if (lvSecteurs.getSelectionModel().getSelectedItem() == null)
        {
            // Gestion de l'alerte pour le secteur non sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Choix du secteur");
            alert.setContentText("Sélectionner un secteur");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if (txtNomStructure.getText().isEmpty())
        {
            // Gestion de l'alerte pour la structure non sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Choix de la structure");
            alert.setContentText("Saisir une structure");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if (txtMontant.getText().isEmpty())
        {
            // Gestion de l'alerte pour le montant non saisi
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Choix du montant");
            alert.setContentText("Saisir un montant");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        //else if(lvVilles.getSelectionModel().getSelectedItem()!= null && lvSecteurs.getSelectionModel().getSelectedItem() != null && !txtNomStructure.getText().isEmpty() && !txtMontant.getText().isEmpty())
        //{
        //   Alert alert = new Alert(Alert.AlertType.INFORMATION);
         //  alert.setTitle("Affectaction réussie");
        //alert.setContentText("Subvention enregistrée");
        //  alert.setHeaderText("");
        // alert.showAndWait();
        }
        else
        {
            String selectedVille = lvVilles.getSelectionModel().getSelectedItem();
            String selectedSecteur = lvSecteurs.getSelectionModel().getSelectedItem();
            int montant = Integer.parseInt(txtMontant.getText());

            Structure laStructure = new Structure(selectedSecteur, montant);

            if (!lesSubventions.containsKey(selectedVille))
            {
                lesSubventions.put(selectedVille, new HashMap<>());
            }

            if (!lesSubventions.get(selectedVille).containsKey(selectedSecteur))
            {
                lesSubventions.get(selectedVille).put(selectedSecteur, new TreeMap<>());
            }

            int annee = cboAnnees.getValue();

            if (!lesSubventions.get(selectedVille).get(selectedSecteur).containsKey(annee))
            {
                lesSubventions.get(selectedVille).get(selectedSecteur).put(annee, new ArrayList<>());
            }

            lesSubventions.get(selectedVille).get(selectedSecteur).get(annee).add(laStructure);
            remplirTreeviewSecteur();
            remplirTreeviewAnnee();
        }
    }

    public void remplirTreeviewAnnee() {
        rootAnnee.getChildren().clear();

        int anneeSelectionnee = (int) cboAnnees.getSelectionModel().getSelectedItem();

        for (String nomVille : lesSubventions.keySet())
        {
            TreeItem<String> noeudVille = new TreeItem<>(nomVille);

            for (String nomSecteur : lesSubventions.get(nomVille).keySet())
            {
                double totalMontantSecteur = 0.0;

                if (lesSubventions.get(nomVille).get(nomSecteur).containsKey(anneeSelectionnee))
                {
                    for (Structure laStructure : lesSubventions.get(nomVille).get(nomSecteur).get(anneeSelectionnee))
                    {
                        totalMontantSecteur += laStructure.getMontant();
                    }
                }

                TreeItem<String> infoSecteur = new TreeItem<>(cboAnnees.getSelectionModel().getSelectedItem().toString() + " : " + totalMontantSecteur);
                noeudVille.getChildren().add(infoSecteur);
            }
            rootAnnee.getChildren().add(noeudVille);
        }
    }


    public void remplirTreeviewSecteur()
    {
        rootSecteur.getChildren().clear();

        for (String nomVille : lesSubventions.keySet())
        {
            TreeItem<String> noeudVille = new TreeItem<>(nomVille);
            noeudVille.setExpanded(true);

            for (String nomSecteur : lesSubventions.get(nomVille).keySet())
            {
                TreeItem<String> noeudSecteur = new TreeItem<>(nomSecteur);
                noeudSecteur.setExpanded(true);

                double totalMontantSecteur = 0.0;

                for (int annee : lesSubventions.get(nomVille).get(nomSecteur).keySet())
                {
                    for (Structure laStructure : lesSubventions.get(nomVille).get(nomSecteur).get(annee))
                    {
                        totalMontantSecteur += laStructure.getMontant();
                    }
                }
                TreeItem<String> infoSecteur = new TreeItem<>(lvSecteurs.getSelectionModel().getSelectedItem().toString() + " : " + totalMontantSecteur);
                noeudSecteur.getChildren().add(infoSecteur);
                noeudVille.getChildren().add(noeudSecteur);
            }
            rootSecteur.getChildren().add(noeudVille);
        }
    }

    @FXML
        public void lvVillesStatsClicked(Event event)
    {

        lvVillesStats.getItems().add(lvVilles.getSelectionModel().getSelectedItem().toString());
    }

}