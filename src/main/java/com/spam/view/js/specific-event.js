$(document).ready(function () {
    getIndividualData(); //TODO edit and delete button
});

function getIndividualData() //get individual event data from other page
{
    var specificEvent = $('#specificevent');

    var anEvent = localStorage.getItem("indivInfo");
    localStorage.clear(); //to clean the localStorage

    var eventId = JSON.parse(anEvent).eventId;
    var org = JSON.parse(anEvent).organization;
    var title = JSON.parse(anEvent).eventTitle;
    var location = JSON.parse(anEvent).location;
    var date = JSON.parse(anEvent).eventDate;
    var time = JSON.parse(anEvent).eventTime;

    var box = '<div class="card-body">'

    var info = '<p>'; //is the element id
    info += 'Event Id: ' + eventId + '<br>'; //for testing
    info += 'Organization: ' + org + '<br>';
    info += 'Title: ' + title + '<br>';
    info += 'Location: ' + location + '<br>';
    info += 'Date: ' + date + '<br>';
    info += 'Time: ' + time + '<br>';
    info += '</p>';

    box += info;
    box += '</div>';

    specificEvent.append(box); //create box with info
}