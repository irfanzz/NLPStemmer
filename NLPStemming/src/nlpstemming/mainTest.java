/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpstemming;

import java.io.FileNotFoundException;
import nlpstemming.Dictionary.ArrayDictionary;

/**
 *
 * @author Achank89
 */
public class mainTest {
    
    public static void main(String[] args) throws FileNotFoundException {
    
        String[] words = tools.getWordsFromFile();
        ArrayDictionary dictionary = new ArrayDictionary(words);
        Stemmer stemmer    = new Stemmer(dictionary);

        System.out.println(stemmer.stem("Algoritma stemming kata pada Bahasa Indonesia dengan performa "
                + "yang paling baik memiliki jenis kesalahan stemming yang paling sedikit adalah "
                + "algoritma Enhanced Confix Stripping ECS Stemmer Meskipun terdapat peningkatan "
                + "performa stemming kata masih terdapat kesalahan yang dilakukan oleh ECS Stemmer "
                + "Selain itu algoritma ECS Stemmer juga tidak mengajukan perbaikan terhadap "
                + "permasalahan overstemming dan understemming Dalam tugas akhir ini diajukan "
                + "perbaikan terhadap algoritma ECS Stemmer Selain perbaikan terhadap aturan pada "
                + "tabel acuan pemenggalan imbuhan juga dilakukan implementasi metode corpus based stemming untuk melakukan penyelesaian terhadap problem overstemming dan understemmingProses evaluasi sistem Information Retrieval IR menggunakan relevansi set dokumen terhadap query yang dibentuk secara otomatis menggunakan teknik data fusion dan metode condorcet Karena tidak dibentuk secara manual relevan set tersebut dinamakan pseudo relevant documents pseudorels"));
        System.out.println(stemmer.stem("Budi belajar membaca dan menulis dengan buku tulis di sekolah"));
    }
}
