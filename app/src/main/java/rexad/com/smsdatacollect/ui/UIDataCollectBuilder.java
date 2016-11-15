package rexad.com.smsdatacollect.ui;


import android.text.InputType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yann Binot Local on 25/10/2016.
 */

public class UIDataCollectBuilder {

    List<SMSDataCollectInput> datainput = new ArrayList<SMSDataCollectInput>();


    public  List<SMSDataCollectInput> buid(){

        datainput.clear();

        int i = 1;
        datainput.add(new SMSDataCollectInput("VIL","Village", InputType.TYPE_CLASS_TEXT,i++));
        datainput.add(new SMSDataCollectInput("COM","Commune", InputType.TYPE_CLASS_TEXT,i++));
        datainput.add(new SMSDataCollectInput("AAMMJJ","Date  (AAMMJJ)", InputType.TYPE_CLASS_DATETIME,i++));
        datainput.add(new SMSDataCollectInput("MM","Mois  de référence (MM)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("EXP","Expéditeur", InputType.TYPE_CLASS_TEXT,i++));
        datainput.add(new SMSDataCollectInput("NBP","Nombre de BP", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("ICFFM","Index Compteur Forage fin de mois",InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("VPO","Volume produit  (m3) (pompe / source / unité de potabilisation)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("VCR","Volume compteur réservoir (m3)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("VCBF","Volume compteurs BF (m3)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("VCBP","Volume compteurs BP (m3)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("VCL","Volume compteurs Lavoirs (m3)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("VCA","Volume compteurs Abreuvoirs (m3)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("RAPMH","Recette attendue PMH (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("RBF","Recette BF (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("RBP","Recette BP (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("RPMH","Recette PMH (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("RLA","Recette Lavoirs (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("RAB","Recette Abreuvoirs (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("RVG","Recette vente au gérant (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("DEX","Dépenses Exploitant (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("DUG","Dépenses UGSPE (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("DEP","Dépôt Epargne (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("REP","Retrait d’Epargne (GNF)", InputType.TYPE_CLASS_NUMBER,i++));
        datainput.add(new SMSDataCollectInput("SCE","Solde Compte Epargne (GNF)", InputType.TYPE_CLASS_NUMBER,i++));

        return datainput;
    }



}
