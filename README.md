
# Gender Statistics Analysis
This project deals with identifying the gender factor in higher education aligned to the geographies that make an impact. The goal of this project is to identify special programs aimed at women across the globe. 
# Requirements
You must find the answers through the development Map Reduce algorithms for the following 
### Business Questions:
	- [X]	Identify the countries where % of female graduates is less than 30%. 
	- [X]	List the average increase in female education in the U.S. from the year 2000.
	- []	List the % of change in male employment from the year 2000. 
	- [ ]	List the % of change in female employment from the year 2000.
	- [ ]	Additionally, based on your data exploration and analysis, evaluate one business factor that you consider important, and make this your own requirement.
	- [ ] 	List the 
### For each business question, there is the following architectural requirements:
	- [ ]	There should be an MR Unit test for each Map, each Reduce and each Map-Reduce combination.
	- [ ]	Data sets must be stored and processed within HDFS and a Hadoop Cluster.
	- [ ]	Performance optimization considerations are a critical factor.
	- [ ]	All Map Reduce solutions should have Javadoc documentation, specifically explaining what was the thought process, approach applied to the problem in question, and any assumptions made.
	- [ ]	Optionally, the configuration of an additional Data Node and showcase of jobs running in a multi-node cluster are encouraged. It is only mandatorily required to run it at least in a single-node cluster.
### Mandatory Technologies
	•	HDFS
	•	Hadoop
	•	MapReduce
	•	MR Unit


# Guidelines and Deadlines
	•	All requirements must be completed.
	•	The data is available in the cloud in the following link in CSV format, multiple files.
	•	Pre-transformation or data cleansing can be done to the data, but if done, it must also be done with Map Reduce. Assume that in real-time you won’t be able to transform Terabytes of data or more with just a grep command, for example.
	•	It’s important to share thoughts and debate with your colleagues, but keep this as a very individual project, because you will only get the most exposure by trying to fix issues yourself in the first place.
	•	You must show the analysis results in your presentation plus any findings, and you will also be asked to run any of these Map Reduce jobs, live.
	•	Project must be presented on Friday of Week 5.
# My Project Questions/Comments/Findings
### Questions
- 
### Challenges Faced
- Deciding on what type of data to use
- Whether or not to clean the null values, to prevent inaccurate results
- How to order/sort the years in #3 so that the calculations on those year's values are accurate.
### What I Learned
- Assigning a variable to the last value of an enhanced for loop without transforming the `Iterable` into an array list.
- My first time reading and filtering data from a .csv in an application
- 
```
 for(DoubleWritable value : values){
            if(index == 0) {
                firstYearPercentage = value.get();
            } else {
				/**
				* Will keep re-assigning variable until the end of
				* the values list. the final assignment represents 
				* the last percentage value we want.
				*/
                lastYearPercentage = value.get(); 
            }
            index += 1;
        }
```
### Process
- I learned that it is really important to look at the data and figure out which variables you want to extract before coding your mapper
### Project Presentation
What strategy did I use? 
1. Business Question
2. My Assumptions (my interpretation of the question)
3. What field did you use?
3. My Findings & meaning of numbers
### TODO (in order)
[ ] Finish #3 (choose one: 1 mapper or Secondary Sorting on Reducer)
[ ] Finalize #5 Requirement question
[ ] Finish #5 Requirement question
[ ] Write All Tests
[ ] Format output to show column at top
[ ] Set `numReducerTasks(0)` for Mapper Only classes
[ ] Use 1 driver with extra argument to run multiple
[ ] Make presentation