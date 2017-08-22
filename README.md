Mail Service
==============

## Run locally

    $ mvn spring-boot:run

## Solution

In order to handle downtime of email services, two clients are used:

    1) Mailgun
    2) Sendgrid

### API Documentation
Send an email
     
     POST /sendmail/
     
Parameters
     
| Name        | Type           | Description  |
| ------------- |:-------------:|:-----|
| to | list of strings      |  The recipients of the email. |
| cc | list of strings      |  The recipients of the email. |
| bcc | list of strings      |  The recipients of the email. |
| subject | string      |  The subject of the email. |
| message | string      |  The body of the email. |
          
Response

          HTTP/1.1 200
          

#### Can be implemented

List all emails
     
     GET /mail/
     
Delete an email
      
      DELETE /email/<id>