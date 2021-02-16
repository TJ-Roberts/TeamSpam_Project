$(document).ready(function () {
    loadUsers();
    toTop();
});

function loadUsers()
{
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/users',
        success: function(userArray)
        {
            var allUsers = $('#allusers');
            $.each(userArray, function(index, user)
            {
                //stringify to be able to display
                var userId = JSON.stringify(user.userId);
                var firstN = JSON.stringify(user.firstName);
                var lastN = JSON.stringify(user.lastName);
                var role = JSON.stringify(user.role);
                var summary = JSON.stringify(user.summary);
                var isOrg = JSON.stringify(user.organizer);

                var box = '<div class="card-body">'

                var details = '<p>';
                details += 'User Id: ' + userId + '<br>';
                details += 'Name: ' + firstN +  ' ' + lastN + '<br>';
                details += 'Role: ' + role + '<br>';
                details += 'Summary: ' + summary + '<br>';
                details += 'Organizer: ' + isOrg + '<br>';
                details += '</p>';

                box += details;
                box += '</div>';

                allUsers.append(box);
            })
        },
        error: function()
        {
            alert('Failed to get users from API');
        }
    })
}

//goes to top of page when goUp button is clicked
function toTop()
{
    $(document).ready(function(){
        $(window).scrollTop(0);
    });
}