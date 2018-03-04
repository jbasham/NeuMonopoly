package models;

public class Board
{
 public Space[] spaces = {
 
		 new SpecialSpace("Start", 10, 10),
		 new NormalProp("Java", 9, 10, "brown", 60, 2, 30, 50),
		 new SpecialSpace("LootBox", 8, 10),
		 new NormalProp(".net", 7, 10, "brown", 60, 4, 30, 50),
		 new SpecialSpace("ClubFees", 6, 10),
		 new Railroad("Frontrunner", 5, 10, 200, 100),
		 new NormalProp("Pluralsight", 4, 10, "lightBlue", 100, 6, 50, 50),
		 new SpecialSpace("Chance", 3, 10),
		 new NormalProp("Codeschool", 2, 10, "lightBlue", 100, 6, 50, 50),
		 new NormalProp("LMS", 1, 10, "lightBlue", 120, 8, 60, 50),
		 new SpecialSpace("Jail", 0, 10),
		 new NormalProp("Bunker", 0, 9, "purple", 140, 10, 70, 100),
		 new Utility("Neumont Cafe", 0, 8, 150, 75),
		 new NormalProp("Classrooms", 0, 7, "purple", 140, 10, 70, 100),
		 new NormalProp("Commons", 0, 6, "purple", 160, 12, 80, 100),
		 new Railroad("TRAX Red Line", 0, 5, 150, 75),
		 new NormalProp("Essex", 0, 4, "orange", 180, 14, 90, 100),
		 new SpecialSpace("LootBox", 0, 3),
		 new NormalProp("City Station", 0, 2, "orange", 180, 14, 90, 100),
		 new NormalProp("Essex", 0, 1, "orange", 200, 16, 100, 100),
		 new SpecialSpace("Free Parking", 0, 0),
		 new NormalProp("Javascript", 1, 0, "red", 220, 18, 110, 150),
		 new SpecialSpace("Chance", 2, 0),
		 new NormalProp("CSS", 3, 0, "red", 220, 18, 110, 150),
		 new NormalProp("HTML", 4, 0, "red", 240, 20, 120, 150),
		 new Railroad("TRAX Blue Line", 5, 0, 150, 75),
		 new NormalProp("Visual Studio", 6, 0, "yellow", 260, 22, 130, 150),
		 new Utility("Food Court", 7, 0, 150, 75),
		 new NormalProp("IntelliJ", 8, 0, "yellow", 260, 22, 130, 150),
		 new NormalProp("Eclipse", 9, 0, "yellow", 280, 24, 140, 150),
		 new SpecialSpace("Go To Jail", 10, 0),
		 new NormalProp("SQL", 10, 1, "green", 300, 26, 150, 200),
		 new SpecialSpace("LootBox", 10, 2),
		 new NormalProp("Perl", 10, 3, "green", 300, 26, 150, 200),
		 new NormalProp("Python", 10, 4, "green", 320, 28, 160, 200),
		 new Railroad("TRAX Green Line", 10, 5, 150, 75),
		 new SpecialSpace("Chance", 10, 6),
		 new NormalProp("C#", 10, 7, "blue", 350, 35, 175, 200),
		 new SpecialSpace("Tuition Fees", 10, 8),
		 new NormalProp("C++", 10, 9, "blue", 400, 50, 200, 200)
 };
 
}
