from flask import Flask
from flask_restful import Resource, Api
from flask_restful import reqparse
import pymysql
from parsers import *

class get_meal(Resource):
    def post(self):
        try:
            parser = reqparse.RequestParser()
            parser.add_argument('school_code', type=str)
            parser.add_argument('meal_code', type=int)
            args = parser.parse_args()
            
            
            rows = getsql("select * from school_3 where school_code = '" + args['school_code'] + "';")
            menu = get_diet(args['meal_code'],args['school_code'],rows[0][4],rows[0][6],datetime.datetime.now())
            print(menu)



            return {'Email': args['email'], 'UserName': args['user_name'], 'Password': args['password']}
        except Exception as e:
            return {'error': str(e)}


def getsql(sql = ''):
    curs.execute(sql)
    return curs.fetchall()




app = Flask(__name__)
api = Api(app)
api.add_resource(get_meal, '/meal')


conn = pymysql.connect(host='localhost', user='root', password='1234', db='korea_school', charset='utf8')
curs = conn.cursor()


if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)