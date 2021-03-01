import java.io.*; 
import java.util.*;
 
public class Main13519097 {
    private static Scanner in = new Scanner(System.in);

    // mengambalikan jumlah vertex/matakuliah yang terdapat pada soal
    static int jumlahVertex(String alamat) {
        int count = 0;
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(alamat));
            while (inFile.hasNext()) {
                String next = inFile.nextLine();
                count++;
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }

        return count;
    }

    // mengembalikan nilai true jika nilai String sudah terdapat pada array vertex
    // dan false jika belum terdapat
    static boolean SudahAda(String vertex[], String Kode) {
        int length = vertex.length;
        boolean ketemu = false;
        for (int i = 0; i < length; i++) {
            if ((vertex[i]).equals(Kode)) {
                ketemu = true;
            }
        }
        return ketemu;
    }

    // mengembalikan index dari sebuah kode matakuliah yang diambil dari array
    // vertex
    static int IndexKode(String vertex[], String Kode) {
        int length = vertex.length;
        int idx = 0;
        boolean ketemu = false;
        while (idx <= length && ketemu == false) {
            if ((vertex[idx]).equals(Kode)) {
                ketemu = true;
            } else {
                idx++;
            }
        }
        return idx;
    }

    // membaca file soal dan mengisi Matriks adjacency
    static void ReadFile(String vertex[], int adjacencyMatrix[][], String alamat) {
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(alamat));
            inFile.useDelimiter("[^A-Za-z0-9]+");
            int index = 0;
            String kodeKuliah, next, currVertex;
            while (inFile.hasNext()) {
                next = inFile.nextLine();
                Scanner kata = new Scanner(next);
                kata.useDelimiter("[^A-Za-z0-9]+");

                // untuk vertex yg pertama
                kodeKuliah = kata.next();
                if (!SudahAda(vertex, kodeKuliah)) {
                    vertex[index] = kodeKuliah;
                    index++;
                }
                currVertex = kodeKuliah;
                int indexVertex1 = IndexKode(vertex, currVertex);
                // System.out.println("...");
                while (kata.hasNext()) {
                    kodeKuliah = kata.next();
                    // System.out.println(kodeKuliah);
                    if (!SudahAda(vertex, kodeKuliah)) {
                        vertex[index] = kodeKuliah;
                        index++;
                    }

                    int indexVertex2 = IndexKode(vertex, kodeKuliah);
                    adjacencyMatrix[indexVertex2][indexVertex1] = 1;

                }
                kata.close();
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }

    }

    // mengisi array indegree berdasarkan nilai dari matriks adjacency
    static void inDegree(int indegree[], int adjacencyMatrix[][], int length) {
        // inisialisasi degree untuk tiap vertex sama dengan 0
        for (int i = 0; i < length; i++) {
            indegree[i] = 0;
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    indegree[j]++;
                }
            }
        }
    }

    // menuliskan solusi pada layar, solusi dapat dilihat pada array urutan
    static void PrintSolusi(String vertex[], int urutan[], int smtMax) {
        boolean awal = true;
        System.out.println("Solusi Persoalan : ");
        for (int i = 1; i <= smtMax; i++) {
            System.out.print("Semester " + i + " : ");
            for (int j = 0; j < urutan.length; j++) {
                if (urutan[j] == i && awal) {
                    System.out.print(vertex[j]);
                    awal = false;
                } else if (urutan[j] == i && !awal) {
                    System.out.print(", " + vertex[j]);
                }
            }
            System.out.println(" ");
            awal = true;
        }

    }

    // memperbaharui nilai/isi dari matriks adjacency setelah penghapusan suatu
    // vertex
    static void RevisiAdjency(int adjacencyMatrix[][], int idx, int length) {
        for (int i = 0; i < length; i++) {
            adjacencyMatrix[idx][i] = 0;
        }
    }

    // mengembalikan nilai true jika semua vertex/mata kuliah "terpilih" (telah
    // diurutkan), dapat dilihat dari array visited
    // dan mengembalikan nilai false jika belum semua "terpilih"
    static boolean cekSudahSemua(boolean visited[]) {
        boolean sudah = true;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                sudah = false;
                break;
            }
        }

        return sudah;
    }

    // algoritma topological sort yang akan digunakan untuk mencari urutan tiap
    // vertex/matakuliah
    static void TopologicalSort(int indegree[], int adjacencyMatrix[][], boolean visited[], String vertex[], 
                        int urutan[],int semester) {
        int length = indegree.length;
        boolean valid = false;
        boolean stop = false;

        // akan dilakukan looping jika belum semua vertex/mata kuliah masuk ke dalam
        // urutan / memiliki urutan
        while (!cekSudahSemua(visited) && !stop) {
            for (int i = 0; i < length; i++) {
                if (indegree[i] == 0 && visited[i] == false) {
                    visited[i] = true;
                    urutan[i] = semester;
                    // perbaiki nilai matriks adjacency
                    RevisiAdjency(adjacencyMatrix, i, length);
                    valid = true;
                }
            }

            // valid akan bernilai false jika tidak terdapat vertex yang degree-nya sama
            // dengan 0
            // artinya tidak ada vertex yang dapat dikeluarkan
            if (!valid) {
                // menuliskan pesan tidak ada solusi
                System.out.println("Solusi Tidak Dapat Ditemukan Karena Soal Tidak Valid (Terdapat Circle)");
                stop = true;
            }
            valid = false;
            semester++;
            // perbaiki nilai array indegree
            inDegree(indegree, adjacencyMatrix, length);
        }

        // menuliskan solusi soal, jika ada
        if (!stop) {
            PrintSolusi(vertex, urutan, semester - 1);
        }
    }

    public static void main(String args[]) {
        // input alamat file yang akan dibaca
        System.out.println(new File("."). getAbsolutePath());
        String alamat;
        System.out.println("Contoh : Contoh1.txt atau /d:/Kuliah/Stima/Tucil2/fix/test/Contoh1.txt");
        System.out.print("Masukkan nama file : ");
        alamat = in.next();

        int jmlVertex;
        // inisialisasi semester
        int semester = 1;
        // menghitung jumlah vertex/matakuliah
        jmlVertex = jumlahVertex(alamat);
        // inisialisasi isi array vertex
        String Vertex[] = new String[jmlVertex];
        for (int i = 0; i < Vertex.length; i++) {
            Vertex[i] = "NULL";
        }
        // inisialisasi isi array urutan
        // array ini menyimpan pada semester berapa matakuliah dapat diambil
        int urutan[] = new int[jmlVertex];
        for (int i = 0; i < urutan.length; i++) {
            urutan[i] = 0;
        }

        // array indegree berisikan berapa banyak matakuliah yag harus diambil sebelum
        // dapat mengambil mata kuliah tersebut
        int indegree[] = new int[jmlVertex];

        // array visited akan menandai apakah suatu vertex/mata kuliah sudah ditentukan
        // urutan/semester nya
        boolean visited[] = new boolean[jmlVertex];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        // matriks adjacency dibuat untuk mengetahui keterkaitan tiap vertex
        int[][] adjacencyMatrix = new int[jmlVertex][jmlVertex];

        //baca file dan isi adjacencyMatrix
        ReadFile(Vertex, adjacencyMatrix,alamat);
        //cari degree tiap vertex
        inDegree(indegree, adjacencyMatrix, jmlVertex);
        //cari solusi menggunakan algoritma topological sort
        TopologicalSort(indegree, adjacencyMatrix, visited, Vertex, urutan, semester);
    }
}
