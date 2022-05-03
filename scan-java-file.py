import javalang

with open("res/hw.java","r",encoding='utf-8') as f:
    tree = javalang.parse.parse(f.read())
    classes = tree.types[0]
    print(classes)
    for i in classes.body:
        print(i)