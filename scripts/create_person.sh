#/bin/sh

# Create Person
curl -i -v \
-X PUT \
-H "Accept:application/json" \
-H "Content-Type:application/json; charset=UTF-8" \
-H "CLIENT_ID: 1" \
-H "API_KEY: cf812f3722f0ff885501330df71396e2d08baacb87cc7c8a6f311b1ddd928915" \
-d '{ "active": true, "birthdate": null, "maritalStatus": null, "id": null, "displayName": "D-Coder Test", "email": "dcoder@ciandt.com", "position": "Internal Competition", "type": "USER", "company": "CI&T", "locale": "pt-br", "gender": "male" }' \
'https://d1-prd.appspot.com/brain/d-coder/people/v2/people'

# Returns:
# {"id":5697423099822080,"displayName":"D-Coder Test","email":"dcoder@ciandt.com","position":"Internal Competition","type":"USER","company":"CI&T","locale":"pt-br","gender":"male","maritalStatus":null,"birthdate":null,"lastUpdate":null,"groups":null,"active":true,"masterPersonId":null}
