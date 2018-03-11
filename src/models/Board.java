package models;

import java.io.Serializable;

public class Board implements Serializable
{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public Space[] spaces;
	public Board() {
		
			Space[] newSpaces = {

			// formats the board (hard-coded values and positions)
			// 0
			new SpecialSpace("Start", 10, 10),
			// 1
			new NormalProp("Java", 9, 10, "brown", 60, 2, 30, 50),
			// 2
			new SpecialSpace("LootBox", 8, 10),
			// 3
			new NormalProp(".net", 7, 10, "brown", 60, 4, 30, 50),
			// 4
			new SpecialSpace("ClubFees", 6, 10),
			// 5
			new Railroad("Frontrunner", 5, 10, 200, 100),
			// 6
			new NormalProp("Pluralsight", 4, 10, "lightBlue", 100, 6, 50, 50),
			// 7
			new SpecialSpace("Chance", 3, 10),
			// 8
			new NormalProp("Codeschool", 2, 10, "lightBlue", 100, 6, 50, 50),
			// 10
			new NormalProp("LMS", 1, 10, "lightBlue", 120, 8, 60, 50),
			// 11
			new SpecialSpace("Jail", 0, 10),
			// 12
			new NormalProp("Bunker", 0, 9, "purple", 140, 10, 70, 100),
			// 13
			new Utility("Neumont Cafe", 0, 8, 150, 75),
			// 14
			new NormalProp("Classrooms", 0, 7, "purple", 140, 10, 70, 100),
			// 15
			new NormalProp("Commons", 0, 6, "purple", 160, 12, 80, 100),
			// 16
			new Railroad("TRAX Red Line", 0, 5, 150, 75),
			// 17
			new NormalProp("Essex", 0, 4, "orange", 180, 14, 90, 100),
			// 18
			new SpecialSpace("LootBox", 0, 3),
			// 19
			new NormalProp("City Station", 0, 2, "orange", 180, 14, 90, 100),
			// 20
			new NormalProp("Elevate on 5th", 0, 1, "orange", 200, 16, 100, 100),
			// 21
			new SpecialSpace("Free Parking", 0, 0),
			// 22
			new NormalProp("Javascript", 1, 0, "red", 220, 18, 110, 150),
			// 23
			new SpecialSpace("Chance", 2, 0),
			// 24
			new NormalProp("CSS", 3, 0, "red", 220, 18, 110, 150),
			// 25
			new NormalProp("HTML", 4, 0, "red", 240, 20, 120, 150),
			// 26
			new Railroad("TRAX Blue Line", 5, 0, 150, 75),
			// 27
			new NormalProp("Visual Studio", 6, 0, "yellow", 260, 22, 130, 150),
			// 28
			new Utility("Food Court", 7, 0, 150, 75),
			// 29
			new NormalProp("IntelliJ", 8, 0, "yellow", 260, 22, 130, 150),
			// 30
			new NormalProp("Eclipse", 9, 0, "yellow", 280, 24, 140, 150),
			// 31
			new SpecialSpace("Go To Jail", 10, 0),
			// 32
			new NormalProp("SQL", 10, 1, "green", 300, 26, 150, 200),
			// 33
			new SpecialSpace("LootBox", 10, 2),
			// 34
			new NormalProp("Perl", 10, 3, "green", 300, 26, 150, 200),
			// 35
			new NormalProp("Python", 10, 4, "green", 320, 28, 160, 200),
			// 36
			new Railroad("TRAX Green Line", 10, 5, 150, 75),
			// 37
			new SpecialSpace("Chance", 10, 6),
			// 38
			new NormalProp("C#", 10, 7, "blue", 350, 35, 175, 200),
			// 39
			new SpecialSpace("Tuition Fees", 10, 8),
			// 40
			new NormalProp("C++", 10, 9, "blue", 400, 50, 200, 200)};
			
			spaces = newSpaces;
	}
	
}
