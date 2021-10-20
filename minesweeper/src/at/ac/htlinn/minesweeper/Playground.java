package at.ac.htlinn.minesweeper;

public class Playground {
	private Field[][] matrix; // (0,0) is top left

	public Playground(int width, int height, int bombs) {
		init(width, height, bombs);
	}

	public void init(int width, int height, int bombs) {
		matrix = new Field[width][height];
		// init matrix with empty fields
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = new EmptyField();
			}
		}
		// place bombs by random
		while (bombs >= 0) {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);
			if (matrix[x][y] instanceof EmptyField) {
				matrix[x][y] = new BombField();
				bombs--;
			}
		}
		// calculate neighbour bomb count
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] instanceof EmptyField) {
					((EmptyField) matrix[i][j]).setBombsCnt(cntNeighbourBombs(i, j));
				}
			}
		}

	}

	/**
	 * count the number of bombs in the direct neighbourhood of position x,y
	 */
	private int cntNeighbourBombs(int x, int y) {
		return isBomb(x - 1, y - 1) + isBomb(x, y - 1) + isBomb(x + 1, y - 1) + isBomb(x + 1, y) + isBomb(x + 1, y + 1)
				+ isBomb(x, y + 1) + isBomb(x - 1, y + 1) + isBomb(x - 1, y);
	}

	/**
	 * @return 1 if bomb, 0 if no bomb or not existing field
	 */
	private int isBomb(int x, int y) {
		if (x < 0 || y < 0 || x > matrix.length - 1 || y > matrix[0].length - 1) {
			return 0;
		}
		if (matrix[x][y] instanceof BombField) {
			return 1;
		}
		return 0;
	}

	public void flagging(int x, int y)
	{
		matrix[x][y].setFlag(!matrix[x][y].isFlag());
	}
	
	public boolean show(int x, int y)
	{
		if (matrix[x][y] instanceof BombField)
		{
			return false;
		}
		
		EmptyField ef = (EmptyField) matrix[x][y];
		ef.setOpen(true);

		 openNeighbourField(x-1, y-1);
		 openNeighbourField(x, y-1);
		 openNeighbourField(x+1, y-1);
		 openNeighbourField(x+1, y);
		 openNeighbourField(x+1, y+1);
		 openNeighbourField(x, y+1);		 
		 openNeighbourField(x-1, y+1);
		 openNeighbourField(x-1, y);		 
		 
		 return true;
	}
	
	private void openNeighbourField(int x, int y)
	{
		//TODO check borders!!
		Field f = matrix[x][y];
		if (f instanceof BombField)
		{
			return;
		} else
		{
			EmptyField ef2 = (EmptyField)f;
			if (ef2.getBombsCnt() == 0)
			{
				show(x,y);
			} else
			{
				ef2.setOpen(true);
			}
		}
	}
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				str += matrix[i][j];
			}
			str += "\n";
		}
		return str;
	}
}