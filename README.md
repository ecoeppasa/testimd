### API "Friend Management"


Security 

username : user

password : 123

1. As a	user, I need an API to create a friend connection between two email addresses.	
```
URI : http://localhost:8080/api/createfriend/
Method : POST
raw input : 
{
    friends:
            [
            'andy@example.com',
            'john@example.com' 
           ]
}
```
2.As a user, I need an API to retrieve the friends list for an email address.
```
URI : http://localhost:8080/api/getfriend/
Method : POST
raw input :
{ 
  email: 'andy@example.com' 
}
```
3. As a user, I need an API to retrieve the common friends list between two email addresses.

note : you can add two or more email addresses
```
URI : http://localhost:8080/api/getcommonfriend/
Method : POST
raw input :
{
  friends:
        [
          'andy@example.com',
          'john@example.com'
        ]
}
```

4. As a user, I need an API to subscribe to updates from an email address.	
```
URI : http://localhost:8080/api/subscribe/
Method : POST
raw input :
{
  "requestor": "lisa@example.com",
  "target": "john@example.com"
}
```

5. As a user, I need an API to block updates from an email address.
```
URI : http://localhost:8080/api/unsubscribe/
Method : POST
raw input :
{
  "requestor": "andy@example.com",
  "target": "john@example.com"
}
```
6. As a user, I need an API to retrieve all email addresses that can receive updates from an email address.
```
URI : http://localhost:8080/api/newsposting/
Method : POST
raw input :
{
  "sender": "john@example.com",
  "text": "Hello World! kate@example.com runail@example.com"
}
```

