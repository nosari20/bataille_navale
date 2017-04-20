package com.cad.bataille_navale.bateaux;

public class Coord {
	public int x;
	public int y;

	public Coord(int posx, int posy) {
		this.x = posx;
		this.y = posy;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coord) {
			Coord c = (Coord) obj;
			if (this.x == c.x && this.y == c.y)
				return true;
		}
		return false;
	}
}
