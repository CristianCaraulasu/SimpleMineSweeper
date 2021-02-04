package pachet;

import java.util.*;

public class MineSweeper {

    public static boolean copie[][];

    public static void umplere(int ind, int j, int valoare, int[][] matrice) {

        if (!( ((ind - 1) < 0 || (ind - 1) == valoare) || ((j - 1) < 0 || (j - 1) == valoare) || matrice[ind - 1][j - 1] == -1 )) // daca este bomba sau sunt in inafara matricii
            matrice[ind - 1][j - 1] += 1;
        if (!( ((ind - 1) < 0 || (ind - 1) == valoare) || ((j) < 0 || (j) == valoare) || matrice[ind - 1][j] == -1))
            matrice[ind - 1][j] += 1;
        if (!( ((ind - 1) < 0 || (ind - 1) == valoare) || ((j + 1) < 0 || (j + 1) == valoare) || matrice[ind - 1][j + 1] == -1 ))
            matrice[ind - 1][j + 1] += 1;
        if (!( ((ind) < 0 || (ind) == valoare) || ((j - 1) < 0 || (j - 1) == valoare) || matrice[ind][j - 1] == -1))
            matrice[ind][j - 1] += 1;
        if (!( ((ind) < 0 || (ind) == valoare) || ((j + 1) < 0 || (j + 1) == valoare) || matrice[ind][j + 1] == -1))
            matrice[ind][j + 1] += 1;
        if (!(  ((ind + 1) < 0 || (ind + 1) == valoare) || ((j - 1) < 0 || (j - 1) == valoare) || matrice[ind + 1][j - 1] == -1))
            matrice[ind + 1][j - 1] += 1;
        if (!( ((ind + 1) < 0 || (ind + 1) == valoare) || ((j) < 0 || (j) == valoare) || matrice[ind + 1][j] == -1))
            matrice[ind + 1][j] += 1;
        if (!(((ind + 1) < 0 || (ind + 1) == valoare) || ((j + 1) < 0 || (j + 1) == valoare) || matrice[ind + 1][j + 1] == -1))
            matrice[ind + 1][j + 1] += 1;
    }
    public static void afisare(int valoare,int [][]matrice){
        System.out.println();
        int nr_bombe=0;
        int nr_descoperite = 0;
        System.out.print("   ");
        for(int i=0; i<valoare;i++)
            System.out.print("  " + i);
        System.out.println();
        System.out.println();
        for(int i=0;i<valoare;i++)
        {
            System.out.print(i + "  ");
            for(int j=0;j<valoare;j++)
                if(copie[i][j] == true){
                    nr_descoperite++;
                    if(matrice[i][j]>0)
                        System.out.print("  " + matrice[i][j]);
                    else
                        System.out.print("   ");
                }
                else {
                    System.out.print("  *");
                    nr_bombe +=1;
                }
            System.out.println();
        }
        System.out.println();
        if(nr_bombe == valoare && nr_descoperite == valoare*valoare-valoare) {
            System.out.println("Bravo sefu, ai castigat!!!");
            System.exit(0);
        }
    }
    public static void hai_sa_descoperim_matricea(int valoarea, int [][]matrice,int linie,int coloana){
        if(matrice[linie][coloana]>0){
            copie[linie][coloana] = true;
            return;
        }
        if(matrice[linie][coloana] == -1)
            return;
        //altfel incercam sa descoperim toate zero-urile posibile
        copie[linie][coloana] = true;
        if(!(linie-1 < 0 || linie-1 >= valoarea || coloana <0 || coloana >= valoarea) && copie[linie-1][coloana] == false)
            hai_sa_descoperim_matricea(valoarea,matrice,linie-1,coloana);
        if(!(linie+1 < 0 || linie+1 >= valoarea || coloana <0 || coloana >= valoarea) && copie[linie+1][coloana] == false)
            hai_sa_descoperim_matricea(valoarea,matrice,linie+1,coloana);
        if(!(linie < 0 || linie >= valoarea || coloana-1 <0 || coloana-1 >= valoarea) && copie[linie][coloana-1] == false)
            hai_sa_descoperim_matricea(valoarea,matrice,linie,coloana-1);
        if(!(linie < 0 || linie >= valoarea || coloana+1 <0 || coloana+1 >= valoarea) && copie[linie][coloana+1] == false)
            hai_sa_descoperim_matricea(valoarea,matrice,linie,coloana+1);

    }
    public static float calculare_indice(int [][]matrice,int valoare){
        List<Pereche> lista = new ArrayList<>();
        int distanta_manhattan=0;

        for(int i=0;i<valoare;i++)
            for(int j=0;j<valoare;j++)
                if(matrice[i][j] == -1){
                    Pereche pereche = new Pereche(i,j);
                    lista.add(pereche);
                }

        for(int i = 0;i<lista.size()-1;i++)
            for(int j=i+1;j<lista.size();j++)
                distanta_manhattan += Math.abs(lista.get(i).a - lista.get(j).a) + Math.abs(lista.get(i).b - lista.get(j).b);
        return (float)distanta_manhattan/(float)(valoare*valoare);
        //pentru 3*3 are valoare intre 0.444 si 0.888
        //pentru 6*6 are valoare intre 0.972 si 2.361
        //pentru 9*9 are valoare intre 1.481 si 4.102
    }
    public static void main(String[] args) {
        System.out.println("Ce dimensiune vrei sa aibe matricea ta?");
        System.out.println("1. 3*3 - va fi un joc cu 3 bombe");
        System.out.println("2. 6*6 - va fi un joc cu 6 bombe");
        System.out.println("3. 9*9 - va fi un joc cu 9 bombe");
        Scanner tastatura = new Scanner(System.in);
        int valoare = tastatura.nextInt();// aici este numarul de bombe
        if (valoare > 3 || valoare < 1) {
            System.out.println("Imi pare rau, nu stii sa folosesti tastatura!");
            System.exit(0); //bye-bye
        }
        valoare *= 3;
        int matrice[][] = new int[valoare][valoare]; // aici este mapa, matricea pe care se joaca
        for (int p[] : matrice)
            Arrays.fill(p, 0); // umplu matricea cu 0
        Random rand = new Random();
        int i = 0;
        while (i < valoare) { // generez pana se umple cu numarul de bombe dorite
            int linie = rand.nextInt(valoare);
            int coloana = rand.nextInt(valoare);
            if (matrice[linie][coloana] == 0) {
                matrice[linie][coloana] = -1;
                i++;
            }
        }

        //incerc sa atribui valorile pozitiilor in functie de bombe
        for (int ind = 0; ind < valoare; ind++)
            for (int j = 0; j < valoare; j++) {
                if (matrice[ind][j] == -1)
                    umplere(ind, j, valoare, matrice);
            }


        for (int ind = 0; ind < valoare; ind++) {
            for (int j = 0; j < valoare; j++)
                if (matrice[ind][j] == -1)
                    System.out.print("  " + matrice[ind][j]);
                else
                    System.out.print("   " + matrice[ind][j]);
            System.out.println();
        } // afisare prima forma de matrice, cea cu punctaje initiale

        boolean terminare = false;
        System.out.println();
        System.out.println("Hai sa ne jucam");
        System.out.println();
        copie = new boolean [valoare][valoare];
        for (boolean p[] : copie)
            Arrays.fill(p, false); // umplu matricea cu 0

        float indice = calculare_indice(matrice,valoare);
        System.out.println("Indice de spatiere : " + indice);
        while(terminare != true){
            afisare(valoare,matrice);

            System.out.print("linie= ");
            int linie = tastatura.nextInt();

            System.out.print("coloana= ");
            int coloana = tastatura.nextInt();

            if(linie < 0 || linie >= valoare || coloana <0 || coloana >= valoare || copie[linie][coloana] == true) {
                System.out.println("Da-mi alte coordonate, pls");
                continue;
            }

            if(matrice[linie][coloana] == -1){
                System.out.println("Ai picat pe bomba...ai pierdut");
                //afisare();
                System.exit(0);
            }
            //daca am ajuns aici inseamna ca suntem pe o pozitie buna
            hai_sa_descoperim_matricea(valoare,matrice,linie,coloana);
        }
    }
}
