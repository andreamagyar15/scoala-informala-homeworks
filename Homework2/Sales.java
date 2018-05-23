import java.util.Arrays;

/**
 Sort an array of representativeName in descending order. The sorting is done based on the amount of revenue generated via sales.
 Each rep has a number of sales, and a quota / sale.
 After the sorting, the first object will be the rep with the most revenue generated.
 The last object in the array will be the rep with the least revenue generated.
 */
public class Sales {
    public static void main(String[] args) {
        RepresentativeSales[] repArray = {
                new RepresentativeSales("Maria", 3, 400),
                new RepresentativeSales("Bogdan", 4, 600),
                new RepresentativeSales("Andrei", 2, 100),
                new RepresentativeSales("Renata", 5, 300),
                new RepresentativeSales("Mihai", 4, 100)
        };

        RepresentativeSales[] sortedReps = new Sales().sortReps(repArray);
        for (int i=0;i< sortedReps.length;i++){
            System.out.println(sortedReps[i].name+" "+sortedReps[i].repSales*sortedReps[i].repQuota);
        }
    }
    private RepresentativeSales[] sortReps(RepresentativeSales[] repsArrayToSort) {
        RepresentativeSales temp = new RepresentativeSales(" ",0,0);
            for (int i = 1; i < repsArrayToSort.length - 1; i++) {
                for (int j = 0; j < repsArrayToSort.length - 1; j++) {
                    if (repsArrayToSort[j].repSales * repsArrayToSort[j].repQuota < repsArrayToSort[j + 1].repSales * repsArrayToSort[j + 1].repQuota) {
                       temp = repsArrayToSort[j + 1];
                       repsArrayToSort[j + 1] = repsArrayToSort[j];
                       repsArrayToSort[j] = temp;
                    }
                }
            }

        return  repsArrayToSort;
    }

}