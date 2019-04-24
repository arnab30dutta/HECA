# House Energy Optimization: Track consumption, Predict expense, Recommend optimization

You have set your monthly household energy consumption at beginning of month to fit your monthly target budget(goal) say 1760 bucks. After few days you checked the expense and felt out of blue that by month end its exceeding. 
     
     Your wishlist demands an App to Predict what will be the expense by end of this month:  
          a. calculate & print exact consumption cost per energy type (eg.  electric / gas / solar / ...etc ) till now.
          b. suggest device timing interval ( eg. run my Washing machine only during low non-peak hours 7-9am)
          c. lets you limit your consumption per energySourceType( e.g min I need Petrol of 14 gallon)
          d. The App will suggest the best optimal use of respective consumption to fit your budget goal
             (eg.  from now calibrate to the following:
                        a. Solar Energy 200 units
                        b. Electric 172 units
                        c. Gas 15L
                        d. Oil 4.2 Litres
                    projected expense : 1740 bucks )
                    
 Refer https://codereview.stackexchange.com/q/217849/198097
 

## Problem Statement : Design a sustainable home tracker and calibrater for power, water, gas to minimize the expense

    Following are the various scenarios to be considered

        Track energy - Track consumption of water, gas and electricity. We should be able extend to capture other type of energies as well (e.g., fuel for vehicles). Apart from storing the consumption and time interval, also look into possibilities of capturing additional attributes for each type and extend it with ease.

        Sustainability goals - Build a construct to provide an ability for users to capture sustainability goals, such as use x amount of alternate energy (wind or solar), cut down the usage by x, shift usage of certain energy to a non-peak time (e.g., running washing machine).

        Savings - Based on goals, project savings ahead of time as well compute them for every given timeframe.

        Suggest goals - Design a mechanism to suggest goals so that folks can pick from existing goals/template and tweak if needed to create their own.

        Other scenarios to keep in mind
            Sustainability score - to build a score for every home
            Badges - Provide badges or incentives for people when they achieve goals
            Incentives - Provide incentives when people achieve certain goals

    Evaluation Criteria

    Pay attention to the following for this exercise for design

        Scalable design
        Data extensibility - ability to quickly extend attributes to consider additional scenarios.
        For all devices - build the application to scale into using any devices or integrate with third party systems


