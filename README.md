# simple-bin-count-simulator
This project creates a virtual Amazon Fulfillment Center and simulates the Simple Bin Count (SBC) inventory management process.

## Project Description
This project creates a virtual Amazon Fulfillment Center and simulates the Simple Bin Count (SBC) inventory management process.

The virtual FC created by this program is modeled after legacy Amazon FCs. Legacy (aka "traditional") Amazon FCs are those without Amazon Robotics (formerly Kiva) technology. The program creates an FC with 76,384 inventory receptacles and assigns each one an unique location address and pick-path ID.

Users can log-in to the SBC process and count the number of items physically present in the bin, basically checking for mismatches between physical and virtual inventory amounts.

## Project Aim
The initial aim of this project was to help me learn good programming fundamentals and to simulate the SBC process.

In 2020, I left my job as an Amazon FC associate in order to pursue a few personal projects, the main one being teaching myself software development.

When thinking of ideas for good portfolio projects, I decided to simulate the SBC process, something I spent many hours doing as an Amazon ICQA (Inventory Control/Quality Assurance) associate.

On a whim, I decided to expand the project's scope by challenging myself to reverse engineer ONT2's (an Amazon legacy FC) pick-path pattern. An efficient pick-path pattern is crucial for a warehouse/FC the size of ONT2, which I estimate has about half a million inventory receptacles, each one requiring an unique and precise pick-path ID. Manual pick-path ID assignment being out of the question for obvious reasons, I was faced with creating my first set of business-critical algorithms.

## Technologies
This program uses Amazon Coretto JDK 17.0.0_35.

I chose Java because this is my first software project, and I read that Java is a good language to learn programming fundamentals.

This program reads-in inventory receptacle locations and their corresponding pick-path IDs from two text files. The values were generated using algorithms that I have decided to omit from this version of the program.

## ETC.
Please note that I have never seen a single line of code pertaining to Amazon FC software. Nor did I receive help/guidance from anybody formerly/currently employed by Amazon. I created the algorithms myself based on my own memories and experiences working as a Level 1 Fulfillment Associate at ONT2, an Amazon FC in San Bernardino, CA.
