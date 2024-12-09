︠e2058c4b-d534-474b-8c56-145c381ea2b5s︠
def showcase_deriving_theorems(coeffs):
    p = RR['x'](coeffs)
    roots = p.roots(ring=RR)
    rootpoints = [(r,0) for r in roots]
    der = derivative(p, x)
    derroots = der.roots(ring=RR)
    derrootpoints =  [(r,0) for r in derroots]
    derder = derivative(der, x)
    res = points(rootpoints, color='red')
    res += plot(p, (x, -3, +3), color='green')
    res += points(derrootpoints, color='yellow')
    res += plot(der, (x, min(derroots)-3, max(derroots)+3), color='blue')
    res += plot(derder, color='pink')
    return res

showcase_deriving_theorems([10,-1,0,3])
︡77921c10-b5eb-475c-bc94-f49b130d9d17︡{"stderr":"Error in lines 15-15\nTraceback (most recent call last):\n  File \"/ext/sage/10.4/src/sage/plot/point.py\", line 392, in point\n    return point2d(points, **kwds)\n           ^^^^^^^^^^^^^^^^^^^^^^^\n  File \"/ext/sage/10.4/src/sage/misc/decorators.py\", line 658, in wrapper\n    return func(*args, **kwds)\n           ^^^^^^^^^^^^^^^^^^^\n  File \"/ext/sage/10.4/src/sage/misc/decorators.py\", line 497, in wrapper\n    return func(*args, **options)\n           ^^^^^^^^^^^^^^^^^^^^^^\n  File \"/ext/sage/10.4/src/sage/plot/point.py\", line 614, in point2d\n    xdata, ydata = xydata_from_point_list(points)\n                   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n  File \"/ext/sage/10.4/src/sage/plot/plot.py\", line 838, in xydata_from_point_list\n    xdata.append(float(x))\n                 ^^^^^^^^\nTypeError: float() argument must be a string or a real number, not 'tuple'\n\nDuring handling of the above exception, another exception occurred:\n\nTraceback (most recent call last):\n  File \"/cocalc/lib/python3.11/site-packages/smc_sagews/sage_server.py\", line 1250, in execute\n    exec(\n  File \"\", line 1, in <module>\n  File \"\", line 9, in showcase_deriving_theorems\n  File \"/ext/sage/10.4/src/sage/plot/point.py\", line 395, in point\n    return point3d(points, **kwds)\n           ^^^^^^^^^^^^^^^^^^^^^^^\n  File \"/ext/sage/10.4/src/sage/misc/decorators.py\", line 658, in wrapper\n    return func(*args, **kwds)\n           ^^^^^^^^^^^^^^^^^^^\n  File \"/ext/sage/10.4/src/sage/plot/plot3d/shapes2.py\", line 1508, in point3d\n    A = sum([Point(z, size, **kwds) for z in v])\n             ^^^^^^^^^^^^^^^^^^^^^^\n  File \"/ext/sage/10.4/src/sage/plot/plot3d/shapes2.py\", line 906, in __init__\n    self.loc = (float(center[0]), float(center[1]), float(center[2]))\n                ^^^^^^^^^^^^^^^^\nTypeError: float() argument must be a string or a real number, not 'tuple'\n"}︡{"done":true}









