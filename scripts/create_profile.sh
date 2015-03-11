#/bin/sh

# Create the profile
# Use id returned from previous call as the personId json attribute below
curl -i \
-H "CLIENT_ID: 1" \
-H "API_KEY: 4138e5e63e900acb213ff48679de35deac380c9a51ab7a9c9ee5dd697e0516a9" \
-H "Accept:application/json" \
-H "Content-Type:application/json; charset=utf-8" \
-H "backdoor-accesskey:079befc173920aaffcc7826389" \
-X POST \
-d '[ { "lastUpdated": null, "personId": 5629499534213120, "birthdate": null, "maritalStatus": null, "imageURL": "https://lh4.googleusercontent.com/-ylL83Hn8XJ8/VHXg9M4Q-XI/AAAAAAAAKJs/NPJU7dS1KUQ/s895-no/FotoPerfil_Square.jpg", "coverURL": "https://lh6.googleusercontent.com/-xT1MINh2gDM/U4KfNDofBDI/AAAAAAAAJIU/Q15YoAlGqq0/s931-fcrop64=1,00002599e494d11f/IMG_2798.jpg", "profileURL": "https://plus.google.com/118239183782204424177", "username": null, "email": "viveiros@ciandt.com", "providerUserId": "118239183782204424177", "providerId": "GooglePlus", "id": null, "displayName": "Daniel Viveiros", "tagLine": null, "introduction": null, "braggingRights": null, "employerName": "CI&T", "jobTitle": "Head of Product Engineering", "locale": "pt-br", "gender": "male", "roles": ["moderator", "publisher"] } ]' \
https://d1-prd.appspot.com/brain/viveiros/admin/v2/backdoor/upsertprofiles