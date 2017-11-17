public class SudokuChecker {
int [][] board = new int[10][10];
public SudokuChecker (int[][] input)
{
board=input;
}
public boolean checkRow(int row, int number)
{
for (int i=1; i<10;i++)
{
if (board[row][i]==number)
{
return false;
}
}
return true;
}
public boolean checkColumn(int column, int number)
{
for (int i=1; i<10;i++)
{
if (board[i][column]==number)
{
return false;
}
}
return true;
}
public boolean checkBox(int row, int column, int number)
{
if (row==1||row==2||row==3)
{
if (column==1||column==2||column==3)
{
for (int i=1;i<4;i++)
{
for (int j=1;j<4;j++)
{
if (board[i][j]==number)
{
return false;
}
}
}
}
}
if (row==4||row==5||row==6)
{
if (column==1||column==2||column==3)
{
for (int i=4;i<7;i++)
{
for (int j=1;j<4;j++)

{
if (board[i][j]==number)
{
return false;
}
}
}
}
}
if (row==7||row==8||row==9)
{
if (column==1||column==2||column==3)
{
for (int i=7;i<10;i++)
{
for (int j=1;j<4;j++)
{
if (board[i][j]==number)
{
return false;
}
}
}
}
}
if (row==1||row==2||row==3)
{
if (column==4||column==5||column==6)
{
for (int i=1;i<4;i++)
{
for (int j=4;j<7;j++)
{
if (board[i][j]==number)
{
return false;
}
}
}
}
}
if (row==4||row==5||row==6)
{
if (column==4||column==5||column==6)
{
for (int i=4;i<7;i++)
{
for (int j=4;j<7;j++)
{
if (board[i][j]==number)
{
return false;
}
}
}
}
}

if (row==7||row==8||row==9)
{
if (column==4||column==5||column==6)
{
for (int i=7;i<10;i++)
{
for (int j=4;j<7;j++)
{
if (board[i][j]==number)
{
return false;
}
}
}
}
}
if (row==1||row==2||row==3)
{
if (column==7||column==8||column==9)
{
for (int i=1;i<4;i++)
{
for (int j=7;j<10;j++)
{
if (board[i][j]==number)
{
return false;
}
}
}
}
}
if (row==4||row==5||row==6)
{
if (column==7||column==8||column==9)
{
for (int i=4;i<7;i++)
{
for (int j=7;j<10;j++)
{
if (board[i][j]==number)
{
return false;
}
}
}
}
}
if (row==7||row==8||row==9)
{
if (column==7||column==8||column==9)
{
for (int i=7;i<10;i++)
{
for (int j=7;j<10;j++)
{
if (board[i][j]==number)

{
return false;
}
}
}
}
}
return true;
}
public boolean checkFinish ()
{
for(int i = 1; i < 10; i++)

{
for(int j = 1; j < 10; j++)
{
if (board[i][j] == 0)
return false;
}
}

return true;
}
public static void main(String[] args) {
// TODO Auto-generated method stub
}
}