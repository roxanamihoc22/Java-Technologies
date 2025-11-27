For Lab 6 Homework I've added the User entity which has the attributes id, email, password and role, and 
this table will contain the credential for different types of users: ADMIN, STUDENT and INSTRUCTOR.
There are also available 2 endpoints: /api/register and /api/login which are responsible for the registration of
a user and respectively authentication of a user using JWT.
Some endpoints were protected in order to be accessed only by certain users: for example students 
preferences cannot be accessed by INSTRUCTORS, only by STUDENTS and ADMIN.