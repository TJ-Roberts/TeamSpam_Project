$(document).ready(function () {
    getUser();
});

function getUser()
{
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/users/' + 1, //1 is default user (organizer) id
        success: function(userArray)
        {
            var userInfo = $('#userinfo');
            var profileName = $('#profilename');

            //stringify to be able to display
            var userId = JSON.stringify(userArray.userId);
            var firstN = JSON.stringify(userArray.firstName);
            var lastN = JSON.stringify(userArray.lastName);
            var isOrg = JSON.stringify(userArray.organizer);
            var role = JSON.stringify(userArray.role);
            var summary = JSON.stringify(userArray.summary);

            //to remove quotes
            var firstName = firstN.replace(/"/g,"");
            var lastName = lastN.replace(/"/g,"");
            var organizer = isOrg.replace(/"/g,"");
            var yourRole = role.replace(/"/g,"");
            var summ = summary.replace(/"/g,"");

            var header = '<p>';
            header += firstName + ' ' + lastName;
            header += '</p>';

            profileName.append(header);

            var box = '<div class="card-body">';

            var info = '<p>';
            info += 'User Id: ' + userId + '<br>';
            info += 'Organizer: ' + organizer + '<br>';
            info += 'Role: ' + yourRole + '<br>';
            info += 'Summary: ' + summ + '<br>';
            info += '</p>';

            box += info;
            box += '</div>';

            userInfo.append(box);
        },
        error: function()
        {
            alert('Failed to get user information');
        }
    })
}