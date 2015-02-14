/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpstemming.view;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import nlpstemming.Dictionary.ArrayDictionary;
import nlpstemming.Marks;
import nlpstemming.SentenceSlicer;
import nlpstemming.Stemmer;
import nlpstemming.Tokenizer;
import nlpstemming.tools;

/**
 *
 * @author Irfan AFif
 */
public class ViewOutput extends javax.swing.JFrame {

    private ArrayList<ArrayList<String>> listWordOriginal;
    private ArrayList<ArrayList<String>> listStemWord;
    private DefaultTableModel tableAdapter;
    private String[] tableHeader = new String[]{
                        "Kata Awal", "Kata Stem"
                    };
    private AbstractListModel<String> adapterList = new AbstractListModel<String>() {

        @Override
        public int getSize() {
            return listWordOriginal.size();
        }

        @Override
        public String getElementAt(int index) {
            return getSentence(listWordOriginal.get(index));
        }
    };
    private ListSelectionListener listSelect = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (jList1.getSelectedIndex() >= 0){
                int index = jList1.getSelectedIndex();
                setTableItem(listWordOriginal.get(index), listStemWord.get(index));
            }
        }
    };

    /**
     * Creates new form ViewOutput
     */
    public ViewOutput() {
        initComponents();
        getContentPane().setBackground(Color.decode("#43609C"));
    }

    public ViewOutput(String input) {
        initComponents();
        jList1.addListSelectionListener(listSelect);
        getContentPane().setBackground(Color.decode("#43609C"));
        try {
            listWordOriginal = new ArrayList<>();
            SentenceSlicer sentenceSlicer = new SentenceSlicer(input);
            ArrayList<ArrayList<String>> listAwal = sentenceSlicer.getListSentence();
            listStemWord = new ArrayList<>(listWordOriginal.size());

            ArrayList<String> words = tools.getWordsFromFile();
            ArrayDictionary dictionary = new ArrayDictionary(words);
            Stemmer stemmer = new Stemmer(dictionary);

            for (int i = 0; i < listAwal.size(); i++) {
                ArrayList<String> sentenceInput = listAwal.get(i);
                ArrayList<String> wordStem = new ArrayList<>(sentenceInput.size());
                listStemWord.add(wordStem);

                Marks marks = new Marks(sentenceInput);
                ArrayList<String> tokenizeList = marks.getSentence();
                Tokenizer tokenizer = new Tokenizer(tokenizeList);
                tokenizeList = tokenizer.getSentence();
                listWordOriginal.add(tokenizeList);
                for (String string : tokenizeList) {
                    wordStem.add(stemmer.stem(string));
                }
            }

            jList1.setModel(adapterList);
            tableAdapter = new javax.swing.table.DefaultTableModel(
                    new Object[][]{
                        {null, null},
                    },tableHeader);
            jTable1.setModel(tableAdapter);
            if (listWordOriginal.size()>0){
                setTableItem(listWordOriginal.get(0), listStemWord.get(0));
                jList1.setSelectedIndex(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSentence(ArrayList<String> listWord) {
        String result = null;
        for (String word : listWord) {
            if (result == null) {
                result = word;
            } else {
                result += " " + word;
            }
        }
        return result;
    }

    private void setTableItem(ArrayList<String> originalWord, ArrayList<String> stemWord){
        String[][] items = new String[originalWord.size()][2];
        for (int i = 0; i < items.length; i++) {
            items[i] = new String[]{originalWord.get(i), stemWord.get(i)};
        }
        tableAdapter.setDataVector(items, tableHeader);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewOutput().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
