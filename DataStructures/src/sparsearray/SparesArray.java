package sparsearray;

/*
* 稀疏数组
* */
public class SparesArray {
    public static void main(String[] args) {
        //创建一个二维数组 11 * 11
        //0：表示没有棋子  1表示黑子  2表示蓝子
        //1. 先遍历二维数组  得到非0的个数
        int chessArr[][]  = new int[11][11];//二维数组
        int sum = 0;//非零的个数
        chessArr[1][2]  = 1;
        chessArr[2][3] = 2;
        chessArr[5][6] = 2;
        for (int[] row : chessArr){
            for (int data:row){
                System.out.printf("%d\t",data);
                if (data != 0){
                    sum++; //非零的个数
                }
            }
            System.out.println();
        }


        //将二维数组  转化为  稀疏数组
        System.out.println(sum);
        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr.length; //多少行
        sparseArr[0][1] = chessArr[0].length; //多少列
        sparseArr[0][2] = sum; //一共几个有效数据
        
        //遍历二维数组，将非0 的数组存放到稀疏数组
        int count = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[0].length; j++) {
                if (chessArr[i][j] !=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2]  = chessArr[i][j];
                }
            }
        }

        //便利稀疏数组
        for (int[] row:sparseArr){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将稀疏数组恢复成二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }
}
