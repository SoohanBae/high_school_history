import json

def change_data(school_name = None, school_code = None, location = None, breakfast= None, lunch= None, dinner= None):

    jstring = open("data.json","r",encoding='utf-8').read()
    dicts = json.loads(jstring)

    print(dicts)

    data = [school_name,school_code,breakfast,lunch,dinner]
    data_name = ['school_name','school_code','breakfast','lunch','dinner']
    loc_dic = {
        '서울특별시' : 'sen',
        '부산광역시' : 'pen',
        '대구광역시' : 'dge',
        '인천광역시' : 'ice',
        '광주광역시' : 'gen',
        '대전광역시' : 'dje',
        '울산광역시' : 'use',
        '세종특별자치시' : '',
        '경기도' : 'goe',
        '강원도' : 'kwe',
        '충청북도' : 'cbe',
        '충청남도' : 'cne',
        '전라북도' : 'jbe',
        '전라남도' : 'jne',
        '경상북도' : 'kbe',
        '경상남도' : 'gne',
        '제주특별자치도' : 'jje'
    }

    if location != None:
        dicts['location'] = loc_dic[location]

    for i in range(len(data)):
        if data[i] != None:
            dicts[data_name[i]] = data[i]

    

    
    jstring2 = json.dumps(dicts,indent=4)
    f = open("data.json","w",encoding='utf-8')
    f.write(jstring2)
    f.close()

change_data(lunch='13:30',location='대구광역시')