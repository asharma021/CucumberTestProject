00:00
it's a prune what you can do you can talk to you are checking the orchestration right and tooling in yes okay so do one thing talk to Swati or Mohit okay I talked to Swati, Swati also don't know she said like she needs little more on that like what needs to be done what we need look what we did

00:28
we did we are having the rrt s schema is there right yes detail schema that is the rrt s schema we are having so that schema we have migrated to new yappid name that is the dq rule repo okay and all the objects from the rrt s okay rrt s underscore dac underscore dq that schema we have migrated to dq rule repo and that's a new

00:54
server, new database, that is our own database. Currently, whatever the objects we are having, that is into the retail database. That's not our database, right? That's our guest database. So, we have procured our new DB and there we have created a new schema name that is a DQRole repo and all the objects from the rrts underscore DAQ we have migrated to DQRole repo.

01:22
So that's why we have to do the testing from that point of view so that all the features, whichever features we are having on the UI, and even the DB jobs we might be having into the autosys, all that we have to do a dry run so that we can able to understand whether we are having any deficiencies or the related to objects or any data.

01:51
that we can able to track it out. That is what the testing is all about. So this scorecard validation if I am not wrong like once the rules is packaged and delivered and it goes to DQP correct like DQP scorecard. So is it the validation regarding the DQ rules in packaging and everything with comparison with the DQP scorecard board?

02:22
That I mean granular level details I am also not having because I have not I mean developed anything there. So I am just doing the migration there. So granular level details I am also not having. So that's why I communicated you to talk to Mohit. Okay. And Swati can help out because she was representing that rule engine, right? So she was providing the demo and everything.

02:52
At the UI level, what needs to be checked and at the back end side for the BAE orchestration, what are the jobs we are having, which has to be executed. That details you can get it from the Moheet or someone working closely with Moheet also. Okay. So they can, they can able to go ahead. Even Yogesh can help out for the sum of the pointers you can able to get from Yogesh as well. Okay. Got it. Sure.

03:21
Because there might be jobs for the data loading, which they have created for the autosys, right? So those jobs also we have to ensure it is executing properly. Because in all the repositories, Snehil has modified the code, okay, at the Java side. So if somewhere in that is not modified, we can able to track it out. We may get the exception.

03:49
those exceptions if we get you can talk to Snehil and Snehil can point out I mean which area is having exactly the exceptions and if we are having any object is missing any object is having into the invalid state in that case we are we can able to fix it out. So currently Dev and SIT we have done. So

04:13
That's why we are doing the testing into the SIT. So once we will have the sign up from you guys, once you guys test it, then only we can proceed for the UAT deployment and the UAT migration. Otherwise we cannot move. So miscellaneous and self-service part, that is done currently. Workbench ECS reports, that is also modified, but that testing is pending. And DA orchestration and rule engine. That part you can just talk to Yogesh, Mohit.

04:43
and Swati. I mean, just you need to understand what is the background about it. As I told you, we have migrated only one schema that is the rrts underscore dq that schema we have migrated to new name dq rule repo. All the objects from the rrts underscore dq are present into the dq rule repo scheme in our own server. And now we have pointed all the

05:12
repos to new server. So that testing we are doing. Okay, got it. Right. And regarding there is one more schema, DQ DSL sandbox, right, for the MSTR one, MSTR box. So that migration we have not yet done. That will be done into the phase two. Okay. Okay. So currently we are testing only for the rrts underscore DAQ scheme, which has been modified to the DQ rule.

05:41
Okay. Understood. Yes. Yes. Yes. Do we have any queries on that? No, I mean, because the background is necessary because there might be a case like if you had a talk with Swati Rokade, she might be not aware about it. So that's why she may you have to give the background. Sure. What exactly we are trying to test. Yeah. So accordingly, they can provide the pointers to us. Sure. Okay, I'll talk to Yogesh and Swati.

06:11
and then if everything is clear there is no any issue then I'll go ahead with the testing right and Snehil has done the modifications for the repos he has done the code changes okay so wherever you can able to find any exceptions or anything you can directly touch the same thing and from the db end if he is not understanding I can able to help him sure sure okay thank you Surendra anything else? No Surendra

06:41
Okay. Thank you. Thank you. Thank you so much. Bye.

