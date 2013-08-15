from pymclevel import TAG_List
from pymclevel import TAG_Byte
from pymclevel import TAG_Int
from pymclevel import TAG_Compound
from pymclevel import TAG_Short
from pymclevel import TAG_Double
from pymclevel import TAG_String


displayName = "Add Custom Simple Buildcraft Pipe"

noop = -1337
inputs = (
	("Pipe Type",19416),
)


def perform(level, box, options):
	pipeId = options["Pipe Type"]
	for x in xrange(box.minx, box.maxx):
		for z in xrange(box.minz, box.maxz):
			for y in xrange(box.miny, box.maxy):
				level.setBlockAt(x,y,z,4093)
				tileEnt=TAG_Compound()
				tileEnt["id"] = TAG_String(u'schematicEntity')
				tileEnt["blockName"]=TAG_String(unicode("BPipe"))
				tileEnt["blockData"]=TAG_String(unicode(""))
				tileEnt["x"] = TAG_Int(x)
				tileEnt["y"] = TAG_Int(y)
				tileEnt["z"] = TAG_Int(z)
				pipeEnt=TAG_Compound()
				pipeEnt["x"] = TAG_Int(x)
				pipeEnt["y"] = TAG_Int(y)
				pipeEnt["z"] = TAG_Int(z)
				pipeEnt["plug[0]"]=TAG_Byte(0)
				pipeEnt["plug[1]"]=TAG_Byte(0)
				pipeEnt["plug[2]"]=TAG_Byte(0)
				pipeEnt["plug[3]"]=TAG_Byte(0)
				pipeEnt["plug[4]"]=TAG_Byte(0)
				pipeEnt["plug[5]"]=TAG_Byte(0)
				pipeEnt["wireSet[0]"]=TAG_Byte(0)
				pipeEnt["wireSet[1]"]=TAG_Byte(0)
				pipeEnt["wireSet[2]"]=TAG_Byte(0)
				pipeEnt["wireSet[3]"]=TAG_Byte(0)
				pipeEnt["facadeBlocks[0]"]=TAG_Int(0)
				pipeEnt["facadeBlocks[1]"]=TAG_Int(0)
				pipeEnt["facadeBlocks[2]"]=TAG_Int(0)
				pipeEnt["facadeBlocks[3]"]=TAG_Int(0)
				pipeEnt["facadeBlocks[4]"]=TAG_Int(0)
				pipeEnt["facadeBlocks[5]"]=TAG_Int(0)
				pipeEnt["facadeMeta[0]"]=TAG_Int(0)
				pipeEnt["facadeMeta[1]"]=TAG_Int(0)
				pipeEnt["facadeMeta[2]"]=TAG_Int(0)
				pipeEnt["facadeMeta[3]"]=TAG_Int(0)
				pipeEnt["facadeMeta[4]"]=TAG_Int(0)
				pipeEnt["facadeMeta[5]"]=TAG_Int(0)
				pipeEnt["pipeId"]=TAG_Int(pipeId)
				pipeEnt["id"]=TAG_String(u"net.minecraft.src.buildcraft.transport.GenericPipe")
				pipeEnt["travelingEntities"]=TAG_List()
				tileEnt["tileEntity"]=pipeEnt
				chunk = level.getChunk(x/16, z/16)
				chunk.TileEntities.append(tileEnt)
				
				chunk.dirty = True