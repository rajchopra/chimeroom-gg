## greytip-hackathon - Team - The Great Gatsby
chimeroom-gg: chimeroom-great-gatsby

#### Problem Statement - Chimeroom - CONFERENCE ROOM BOOKING

> If you had to identify, in one word, the reason why the human race has not achieved and never will achieve, its full potential, that word would be 'meetings. - Dave Barry

Meetings are an indispensable part of any business. However, any respectable meeting needs to be conducted in a proper conference room. Someone asked the CEO why the company performed horribly in the last quarter. "Low productivity, dammit! People are wasting too much time hunting around for free conference rooms.", he claimed, without a trace of irony.

Your team is now being asked to deliver a conference room booking solution by tomorrow morning. Or else.

There are more than 10 conference rooms of all sizes and shapes: small interview rooms, medium sized meeting rooms, training room, a board room. The rooms are across 4 levels of the building. Right now, the rooms are occupied on a first come first serve basis. (People do use handkerchiefs to book a room, but that's not really working out). During meetings, it is not unusual to get kicked off as another 'very important' meeting is planned in the same room.

#### Requirements

 - Develop a web-based solution that provides:
 - Users to submit a request for meeting rooms.
 - Automatic room allocation based on user requirement (seating capacity, facilities, etc.)

#### Create Conference Rooms
 - Provision to create/ modify/ view/ delete rooms
 - A room shall have the following parameters - Name, Capacity, Floor
 - Provision to display the facilities available. Given a specimen list as Annexure -1
 - Provision to flag a room as inactive (during renovation/ repair) and this is primarily to stop accepting booking requests

#### Room Booking Request
 - Provision to give a request for meetings with number of participants and purpose of the meeting.
 - Provision to view the room that is allocated for the requested meeting.

#### Booking Cancellation
 - User shall be able to cancel bookings.

#### Dashboard to see the occupancy and process progress(optional)
 - Provision for user to see number of bookings across, room wise, for a date range
#### Event driven emails and notifications

Annexure -1 : List of facilities

 - White Board 
 - Projector 
 - Internet connectivity 
 - Wi-fi 
 - Intercom facility 
 - Tele-conferencing facility 
 - Video-conferencing facility

## Our solution:

1. Client - API setup using Java Play Framework which would handle basic CRUD operations
2. Backend service written in python - Manages allocations and evolve into a logic engine.
