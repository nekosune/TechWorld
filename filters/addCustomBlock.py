from pymclevel import TAG_List
from pymclevel import TAG_Byte
from pymclevel import TAG_Int
from pymclevel import TAG_Compound
from pymclevel import TAG_Short
from pymclevel import TAG_Double
from pymclevel import TAG_String

displayName = "Add Custom Mob Block"

noop = -1337
inputs = (
	("Block Name",("string", "value=")),
	("Block Data",("string", "value="))
)


def perform(level, box, options):
	name = options["Block Name"]
	data=options["Block Data"]
	for x in xrange(box.minx, box.maxx):
		for z in xrange(box.minz, box.maxz):
			for y in xrange(box.miny, box.maxy):
				level.setBlockAt(x,y,z,4093)
				tileEnt=TAG_Compound()
				tileEnt["id"] = TAG_String(u'schematicEntity')
				tileEnt["blockName"]=TAG_String(unicode(name))
				tileEnt["blockData"]=TAG_String(unicode(data))
				tileEnt["x"] = TAG_Int(x)
				tileEnt["y"] = TAG_Int(y)
				tileEnt["z"] = TAG_Int(z)
				chunk = level.getChunk(x/16, z/16)
				chunk.TileEntities.append(tileEnt)
				chunk.dirty = True